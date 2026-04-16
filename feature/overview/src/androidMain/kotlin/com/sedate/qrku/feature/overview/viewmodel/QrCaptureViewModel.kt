package com.sedate.qrku.feature.overview.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.common.constants.SeConst.FIVE_THOUSAND
import com.sedate.qrku.core.database.repository.BarcodeRepository
import com.sedate.qrku.core.datastore.preference.settings.SettingsPreference
import com.sedate.qrku.core.model.SettingsData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@Stable
actual class QrCaptureViewModel(
	private val repository: BarcodeRepository,
	settingsPreference: SettingsPreference
) : ViewModel() {
	private val _confirmUrl = MutableStateFlow<String?>(null)
	val confirmUrl = _confirmUrl.asStateFlow()

	val settingsData: StateFlow<SettingsData> =
		settingsPreference.settingsFlow
			.map { state -> state }
			.stateIn(
				scope = viewModelScope,
				started = SharingStarted.WhileSubscribed(Long.FIVE_THOUSAND),
				initialValue = SettingsData()
			)

	fun saveResult(
		actionType: BarcodeType.Action,
		value: String,
		contentType: String,
		format: String,
		formatCode: Int?
	) {
		viewModelScope.launch {
			if (actionType == BarcodeType.Action.SCAN) {
				repository.saveScan(
					result = value,
					contentType = contentType,
					format = format,
					formatCode = formatCode
				)
			} else {
				repository.saveCreate(
					content = value,
					contentType = contentType,
					format = format,
					formatCode = formatCode
				)
			}
		}
	}

	fun showConfirmDialog(url: String) {
		_confirmUrl.value = url
	}

	fun dismissDialog() {
		_confirmUrl.value = null
	}
}
