package com.sedate.qrku.feature.scan.contract

import com.sedate.qrku.core.model.ScanResult

actual class ScanController {
	actual fun startScan(
		onResult: (ScanResult) -> Unit
	) {
	}

	actual fun stopScan() {}

	actual fun toggleFlash(enabled: Boolean) {}
	actual fun scanFromImage(
		uri: Any,
		onResult: (ScanResult) -> Unit
	) {
	}
}