package com.sedate.qrku.feature.overview.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedate.qrku.core.common.constants.BarcodeType
import com.sedate.qrku.core.database.repository.BarcodeRepository
import kotlinx.coroutines.launch

@Stable
actual class QrCaptureViewModel(
	private val repository: BarcodeRepository
) : ViewModel() {
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
}
