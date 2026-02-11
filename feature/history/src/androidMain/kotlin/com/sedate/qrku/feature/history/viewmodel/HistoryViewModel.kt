package com.sedate.qrku.feature.history.viewmodel

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sedate.qrku.core.common.constants.SeConst.ZERO
import com.sedate.qrku.core.common.utils.formatDateHeader
import com.sedate.qrku.core.database.repository.BarcodeRepository
import com.sedate.qrku.core.model.BarcodeHistory
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

sealed interface HistoryUiEvent {
	data class ShowToast(val message: String) : HistoryUiEvent
}

@Stable
actual class HistoryViewModel(
	private val repository: BarcodeRepository
) : ViewModel() {
	private val _uiEvent = MutableSharedFlow<HistoryUiEvent>()
	val uiEvent = _uiEvent.asSharedFlow()

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
		_historyData.value = repository.getHistory()
			.groupBy { history ->
				formatDateHeader(history.createdAt)
			}
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
}