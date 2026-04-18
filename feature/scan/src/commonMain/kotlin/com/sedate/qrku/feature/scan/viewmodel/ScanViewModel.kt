package com.sedate.qrku.feature.scan.viewmodel

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedate.qrku.core.common.constants.SeConst.FIVE_THOUSAND
import com.sedate.qrku.core.common.constants.SeConst.ONE
import com.sedate.qrku.core.common.constants.SeConst.ZERO
import com.sedate.qrku.core.datastore.preference.settings.SettingsPreference
import com.sedate.qrku.core.model.ScanResult
import com.sedate.qrku.core.model.SettingsData
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Stable
class ScanViewModel(
	settingsPreference: SettingsPreference
) : ViewModel() {
	private var hasScanned = false

	private val _scanEvent = MutableSharedFlow<ScanResult>(
		replay = Int.ZERO,
		extraBufferCapacity = Int.ONE
	)
	val scanEvent = _scanEvent.asSharedFlow()
	val settingsData: StateFlow<SettingsData> =
		settingsPreference.settingsFlow
			.map { state -> state }
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(Long.FIVE_THOUSAND),
				initialValue = SettingsData()
			)

	private var _zoomRatio = MutableStateFlow(Float.ONE)
	val zoomRatio = _zoomRatio.asStateFlow()

	private var _minZoom = MutableStateFlow(Float.ONE)
	val minZoom = _minZoom.asStateFlow()

	private var _maxZoom = MutableStateFlow(Float.ONE)
	val maxZoom = _maxZoom.asStateFlow()

	private val _isFlash = MutableStateFlow(false)
	val isFlash = _isFlash.asStateFlow()

	private val _isBrowserOpen = MutableStateFlow(false)
	val isBrowserOpen = _isBrowserOpen.asStateFlow()

	private val _confirmUrl = MutableStateFlow<String?>(null)
	val confirmUrl = _confirmUrl.asStateFlow()

	fun emitScan(
		result: ScanResult
	) {
		if (hasScanned) return
		hasScanned = true

		_scanEvent.tryEmit(result)
	}

	fun resetScan() {
		hasScanned = false
	}

	fun setZoomRatio(
		zoomRatio: Float,
		minZoomRatio: Float? = null,
		maxZoomRatio: Float? = null
	) {
		_zoomRatio.value = zoomRatio

		if (minZoomRatio != null && maxZoomRatio != null) {
			_minZoom.value = minZoomRatio
			_maxZoom.value = maxZoomRatio
		}
	}

	fun setFlash(isFlash: Boolean): Boolean {
		_isFlash.value = isFlash

		return isFlash
	}

	fun setBrowserOpen(isOpen: Boolean) {
		_isBrowserOpen.value = isOpen
	}

	fun showConfirmDialog(url: String) {
		_confirmUrl.value = url
	}

	fun dismissDialog() {
		_confirmUrl.value = null
	}
}
