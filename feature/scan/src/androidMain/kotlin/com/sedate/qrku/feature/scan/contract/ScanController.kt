package com.sedate.qrku.feature.scan.contract

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage
import com.sedate.qrku.core.model.ScanResult

actual class ScanController(
	private val context: Context
) {
	private var cameraProvider: ProcessCameraProvider? = null
	private var previewView: PreviewView? = null
	private var lifecycleOwner: LifecycleOwner? = null
	private var camera: Camera? = null
	private var pendingFlash: Boolean = false

	fun attachPreview(
		previewView: PreviewView,
		lifecycleOwner: LifecycleOwner
	) {
		this.previewView = previewView
		this.lifecycleOwner = lifecycleOwner
	}

	@OptIn(ExperimentalGetImage::class)
	actual fun startScan(
		onResult: (ScanResult) -> Unit
	) {
		val pv = previewView ?: return
		val owner = lifecycleOwner ?: return

		val providerFuture = ProcessCameraProvider.getInstance(context)

		providerFuture.addListener(
			{
				cameraProvider = providerFuture.get()

				val preview = buildPreview(pv)
				val analysis = buildAnalysis(onResult)

				cameraProvider?.apply {
					unbindAll()
					camera = bindToLifecycle(
						owner,
						CameraSelector.DEFAULT_BACK_CAMERA,
						preview,
						analysis
					)
					if (camera?.cameraInfo?.hasFlashUnit() == true) {
						camera?.cameraControl?.enableTorch(pendingFlash)
					}
				}
			},
			ContextCompat.getMainExecutor(context)
		)
	}

	actual fun stopScan() {
		camera?.cameraControl?.enableTorch(false)
		cameraProvider?.unbindAll()
		camera = null
	}

	actual fun toggleFlash(enabled: Boolean) {
		pendingFlash = enabled
		camera?.cameraControl?.enableTorch(enabled)
	}

	actual fun scanFromImage(
		uri: Any,
		onResult: (ScanResult) -> Unit
	) {
		val imageUri = uri as? Uri ?: return

		val inputImage = InputImage.fromFilePath(
			context,
			imageUri
		)

		barcodeScanner.process(inputImage)
			.addOnSuccessListener { barcodes ->
				val barcode = barcodes.firstOrNull()
				val value = barcode?.rawValue ?: return@addOnSuccessListener
				val format = barcode.format

				onResult(
					ScanResult(
						value = value,
						format = format
					)
				)
			}
			.addOnFailureListener { }
	}

	private fun buildPreview(pv: PreviewView): Preview =
		Preview.Builder()
			.build()
			.also { it.surfaceProvider = pv.surfaceProvider }

	private fun buildAnalysis(
		onResult: (ScanResult) -> Unit
	): ImageAnalysis =
		ImageAnalysis.Builder()
			.setBackpressureStrategy(
				ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
			)
			.build()
			.also { analysis ->
				analysis.setAnalyzer(
					ContextCompat.getMainExecutor(context)
				) { proxy ->
					analyzeImage(
						proxy,
						onResult
					)
				}
			}

	@OptIn(ExperimentalGetImage::class)
	private fun analyzeImage(
		proxy: ImageProxy,
		onResult: (ScanResult) -> Unit
	) {
		val mediaImage = proxy.image ?: run {
			proxy.close()
			return
		}

		val inputImage = InputImage.fromMediaImage(
			mediaImage,
			proxy.imageInfo.rotationDegrees
		)

		barcodeScanner.process(inputImage)
			.addOnSuccessListener { barcodes ->
				val barcode = barcodes.firstOrNull()
				val value = barcode?.rawValue ?: return@addOnSuccessListener
				val format = barcode.format

				onResult(
					ScanResult(
						value = value,
						format = format
					)
				)
			}
			.addOnFailureListener { }
			.addOnCompleteListener {
				proxy.close()
			}
	}

	private val barcodeScanner: BarcodeScanner =
		BarcodeScanning.getClient(
			BarcodeScannerOptions.Builder()
				.setBarcodeFormats(
					Barcode.FORMAT_ALL_FORMATS
				)
				.build()
		)
}
