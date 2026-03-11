package com.sedate.qrku.feature.scan.ui.view

import android.Manifest
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst.EMPTY
import com.sedate.qrku.core.model.ScanResult
import com.sedate.qrku.core.ui.navigation.Navigator
import com.sedate.qrku.core.ui.navigation.OverviewRoute
import com.sedate.qrku.core.ui.utils.rememberCameraPermissionState
import com.sedate.qrku.feature.scan.contract.ScanController
import com.sedate.qrku.feature.scan.ui.components.CameraPermissionContent
import com.sedate.qrku.feature.scan.ui.components.CameraPreview
import com.sedate.qrku.feature.scan.ui.components.ScannerCornerOverlay
import com.sedate.qrku.feature.scan.ui.components.ScannerDarkOverlay
import com.sedate.qrku.feature.scan.ui.components.ScannerTopBar
import com.sedate.qrku.feature.scan.ui.components.rememberGalleryPicker
import com.sedate.qrku.feature.scan.viewmodel.ScanViewModel
import org.koin.compose.koinInject

@Composable
@RequiresPermission(Manifest.permission.VIBRATE)
actual fun ScanView(
	navigator: Navigator,
	viewModel: ScanViewModel
) = with(viewModel) {
	val lifecycleOwner = LocalLifecycleOwner.current
	val controller: ScanController = koinInject()
	val permission = rememberCameraPermissionState()
	val settingsData by settingsData.collectAsState()

	val isFlash by isFlash.collectAsState()
	var isGalleryOpen by rememberSaveable { mutableStateOf(false) }

	val openGallery = rememberGalleryPicker(
		onImagePicked = { uri ->
			isGalleryOpen = false

			controller.scanFromImage(uri) { result ->
				emitScan(
					ScanResult(
						result.value,
						result.format
					)
				)
			}
		},
		onCancel = {
			isGalleryOpen = false
			controller.startScan { result ->
				emitScan(
					ScanResult(
						result.value,
						result.format
					)
				)
			}
		}
	)

	when {
		permission.isGranted() -> {
			LaunchedEffect(Unit) {
				viewModel.resetScan()

				controller.startScan { result ->
					emitScan(
						ScanResult(
							result.value,
							result.format
						)
					)
				}

				scanEvent.collect { result ->
					if (settingsData.isBeepEnabled) controller.beep()
					if (settingsData.isVibrateEnabled) controller.vibrate()

					navigator.navigate(
						OverviewRoute.QrCapture(
							actionType = BarcodeType.Action.SCAN,
							type = String.EMPTY,
							input = result.value,
							format = result.format
						)
					)
				}
			}

			DisposableEffect(Unit) {
				onDispose { controller.stopScan() }
			}

			Box(Modifier.fillMaxSize()) {
				CameraPreview(
					Modifier.fillMaxSize(),
					onPreviewReady = { previewView ->
						controller.attachPreview(
							previewView = previewView,
							lifecycleOwner = lifecycleOwner
						)
					}
				)

				ScannerDarkOverlay()
				ScannerCornerOverlay(this)

				ScannerTopBar(
					isFlash = isFlash,
					onFlashClick = {
						val isFlash = setFlash(isFlash.not())
						controller.toggleFlash(isFlash)
					},
					onGalleryClick = {
						isGalleryOpen = true
						controller.stopScan()
						openGallery()
					}
				)
			}
		}

		else -> {
			CameraPermissionContent(
				permanentlyDenied = permission.isPermanentlyDenied(),
				onRequest = permission.request,
				onOpenSettings = permission.openSettings
			)
		}
	}
}