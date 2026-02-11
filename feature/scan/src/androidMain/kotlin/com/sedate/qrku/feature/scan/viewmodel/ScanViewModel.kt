package com.sedate.qrku.feature.scan.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import com.sedate.qrku.core.model.ScanResult
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

@Stable
actual class ScanViewModel() : ViewModel() {
	private val _scanEvent = MutableSharedFlow<ScanResult>(
		replay = 0,
		extraBufferCapacity = 1
	)
	actual val scanEvent = _scanEvent.asSharedFlow()

	private var hasScanned = false

	private val _isFlash = MutableStateFlow(false)
	actual val isFlash = _isFlash.asStateFlow()

	actual fun setFlash(isFlash: Boolean): Boolean {
		_isFlash.value = isFlash

		return isFlash
	}

	actual fun emitScan(
		result: ScanResult
	) {
		if (hasScanned) return
		hasScanned = true

		_scanEvent.tryEmit(result)
	}

	actual fun resetScan() {
		hasScanned = false
	}
}
