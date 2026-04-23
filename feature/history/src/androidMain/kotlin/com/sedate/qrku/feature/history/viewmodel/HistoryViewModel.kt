package com.sedate.qrku.feature.history.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedate.qrku.core.common.constants.SeConst.ZERO
import com.sedate.qrku.core.common.utils.formatDateHeader
import com.sedate.qrku.core.database.repository.BarcodeRepository
import com.sedate.qrku.core.model.BarcodeHistory
import com.sedate.qrku.core.model.IconKey
import com.sedate.qrku.resources.Res
import com.sedate.qrku.resources.barcode_ic
import com.sedate.qrku.resources.calendar_ic
import com.sedate.qrku.resources.contact_ic
import com.sedate.qrku.resources.identity_ic
import com.sedate.qrku.resources.link_ic
import com.sedate.qrku.resources.mail_ic
import com.sedate.qrku.resources.phone_ic
import com.sedate.qrku.resources.playstore_ic
import com.sedate.qrku.resources.sms_ic
import com.sedate.qrku.resources.text_ic
import com.sedate.qrku.resources.wifi_ic
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource

sealed interface HistoryUiEvent {
	data class ShowToast(val message: String) : HistoryUiEvent
}

@Stable
actual class HistoryViewModel(
	private val repository: BarcodeRepository
) : ViewModel() {
	private val _uiEvent = MutableSharedFlow<HistoryUiEvent>()
	val uiEvent = _uiEvent.asSharedFlow()

	private val _isLoading = MutableStateFlow(true)
	val isLoading = _isLoading.asStateFlow()

	private val _historyData = MutableStateFlow<Map<String, List<BarcodeHistory>>>(emptyMap())
	val historyData = _historyData.asStateFlow()

	private val _selectedIds = MutableStateFlow<Set<Long>>(emptySet())
	val selectedIds = _selectedIds.asStateFlow()

	val isSelectionMode: StateFlow<Boolean> =
		selectedIds.map { it.isNotEmpty() }
			.stateIn(
				viewModelScope,
				SharingStarted.Eagerly,
				false
			)

	suspend fun getHistory() {
		_isLoading.value = true
		_historyData.value = repository.getHistory()
			.groupBy { history ->
				formatDateHeader(history.createdAt)
			}
		_isLoading.value = false
	}

	fun toggleSelect(id: Long) {
		_selectedIds.update {
			if (id in it) it - id else it + id
		}
	}

	fun clearSelection() {
		_selectedIds.value = emptySet()
	}

	fun selectAll() {
		val allIds = historyData.value
			.values
			.flatten()
			.map { it.id }
			.toSet()

		_selectedIds.value = allIds
	}

	fun deleteSelected() {
		viewModelScope.launch {
			val count = _selectedIds.value.size
			if (count == Int.ZERO) return@launch

			repository.deleteByIds(_selectedIds.value.toList())
			_selectedIds.value = emptySet()
			getHistory()

			_uiEvent.emit(
				HistoryUiEvent.ShowToast("$count item(s) deleted")
			)
		}
	}

	fun IconKey.toDrawable(): DrawableResource? =
		when (this) {
			IconKey.CONTACT -> Res.drawable.contact_ic
			IconKey.PHONE -> Res.drawable.phone_ic
			IconKey.EMAIL -> Res.drawable.mail_ic
			IconKey.MESSAGE -> Res.drawable.sms_ic
			IconKey.TEXT -> Res.drawable.text_ic
			IconKey.LINK -> Res.drawable.link_ic
			IconKey.WIFI -> Res.drawable.wifi_ic
			IconKey.CALENDAR -> Res.drawable.calendar_ic
			IconKey.VCARD -> Res.drawable.identity_ic
			IconKey.PLAY_STORE -> Res.drawable.playstore_ic
			IconKey.BARCODE -> Res.drawable.barcode_ic
			else -> null
		}
}