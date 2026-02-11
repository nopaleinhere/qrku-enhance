package com.sedate.qrku.feature.scan.contract

import androidx.lifecycle.LifecycleOwner
import com.sedate.qrku.core.model.ScanResult

expect class ScanController {
	fun startScan(
		onResult: (ScanResult) -> Unit
	)

	fun stopScan()

	fun toggleFlash(enabled: Boolean)

	fun scanFromImage(
		uri: Any,
		onResult: (ScanResult) -> Unit
	)
}