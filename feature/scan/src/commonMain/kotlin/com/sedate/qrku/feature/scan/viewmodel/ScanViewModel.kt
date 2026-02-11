package com.sedate.qrku.feature.scan.viewmodel

import androidx.compose.runtime.Stable
import com.sedate.qrku.core.model.ScanResult
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

@Stable
expect class ScanViewModel {
	val scanEvent: SharedFlow<ScanResult>

	val isFlash: StateFlow<Boolean>

	fun setFlash(isFlash: Boolean): Boolean

	fun emitScan(result: ScanResult)

	fun resetScan()
}