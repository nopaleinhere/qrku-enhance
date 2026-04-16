package com.sedate.qrku.feature.scan.ui.view

import android.Manifest
import android.view.ViewTreeObserver
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.LocalView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst.EMPTY
import com.sedate.qrku.core.common.constants.SeConst.THREE_HUNDRED
import com.sedate.qrku.core.common.utils.extractDomain
import com.sedate.qrku.core.common.utils.isUrl
import com.sedate.qrku.core.common.utils.normalizeUrl
import com.sedate.qrku.core.model.ScanResult
import com.sedate.qrku.core.model.SettingsData
import com.sedate.qrku.core.ui.material.dialog.SeBottomDialog
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
@RequiresPermission(Manifest.permission.VIBRATE)
actual fun ScanView(
	navigator: Navigator,
	viewModel: ScanViewModel
) = with(viewModel) {
	val context = LocalContext.current
	val lifecycleOwner = LocalLifecycleOwner.current
	val view = LocalView.current
	val uriHandler = LocalUriHandler.current
	val scope = rememberCoroutineScope()
	val permission = rememberCameraPermissionState()
	val controller: ScanController = koinInject()
	val settingsData by settingsData.collectAsState()
	val isFlash by isFlash.collectAsState()
	val isBrowserOpen = remember { mutableStateOf(false) }
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
					handleScanResult(
						settings = settingsData,
						navigator = navigator,
						viewModel = viewModel,
						controller = controller,
						result = result,
						openLink = { url ->
							isBrowserOpen.value = true
							controller.stopScan()

							uriHandler.openUri(url)
						}
					)
				}
			}

			DisposableEffect(Unit) {
				onDispose { controller.stopScan() }
			}

			DisposableEffect(view) {
				val listener = ViewTreeObserver.OnWindowFocusChangeListener { hasFocus ->
					handleWindowFocus(
						hasFocus = hasFocus,
						isBrowserOpen = isBrowserOpen,
						controller = controller,
						viewModel = viewModel,
						emitScan =
							::emitScan
					)
				}
				view.viewTreeObserver.addOnWindowFocusChangeListener(listener)
				onDispose { view.viewTreeObserver.removeOnWindowFocusChangeListener(listener) }
			}


			ConfirmUrlDialog(
				viewModel = viewModel,
				openLink = { url ->
					controller.stopScan()
					uriHandler.openUri(url)
					scope.launch {
						delay(Long.THREE_HUNDRED)
						isBrowserOpen.value = true
					}
				}
			)

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

@Composable
fun ConfirmUrlDialog(
	viewModel: ScanViewModel,
	openLink: (String) -> Unit
) = with(viewModel) {
	val confirmUrl by confirmUrl.collectAsState()

	confirmUrl?.let { url ->
		val normalized = normalizeUrl(url)
		val domain = extractDomain(normalized)

		SeBottomDialog(
			icon = Icons.Default.OpenInBrowser,
			title = "Open link?",
			message = domain,
			description = normalized,
			onConfirm = {
				openLink(normalized)
				dismissDialog()
				resetScan()
			},
			onDismiss = {
				dismissDialog()
				resetScan()
			}
		)
	}
}

@RequiresPermission(Manifest.permission.VIBRATE)
fun handleScanResult(
	settings: SettingsData,
	navigator: Navigator,
	viewModel: ScanViewModel,
	controller: ScanController,
	result: ScanResult,
	openLink: (String) -> Unit
) {
	val value = result.value

	if (settings.isBeepEnabled) controller.beep()
	if (settings.isVibrateEnabled) controller.vibrate()

	if (settings.isAutoOpenEnabled && value.isUrl()) {
		if (settings.isConfirmBeforeOpenEnabled.not()) {
			val normalized = normalizeUrl(value)
			openLink(normalized)
		} else {
			viewModel.showConfirmDialog(value)
		}
	} else {
		navigator.navigate(
			OverviewRoute.QrCapture(
				actionType = BarcodeType.Action.SCAN,
				type = String.EMPTY,
				input = value,
				format = result.format
			)
		)
	}
}

fun handleWindowFocus(
	hasFocus: Boolean,
	isBrowserOpen: MutableState<Boolean>,
	controller: ScanController,
	viewModel: ScanViewModel,
	emitScan: (ScanResult) -> Unit
) {
	if (hasFocus) {
		onReturnFromBrowser(
			isBrowserOpen,
			controller,
			viewModel,
			emitScan
		)
	}
}

fun onReturnFromBrowser(
	isBrowserOpen: MutableState<Boolean>,
	controller: ScanController,
	viewModel: ScanViewModel,
	emitScan: (ScanResult) -> Unit
) {
	if (isBrowserOpen.value) {
		isBrowserOpen.value = false
		viewModel.resetScan()
		controller.startScan { result ->
			emitScan(
				ScanResult(
					result.value,
					result.format
				)
			)
		}
	}
}