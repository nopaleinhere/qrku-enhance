package com.sedate.qrku.core.common.state

import androidx.compose.runtime.Stable
import com.sedate.qrku.core.common.constants.SeConst.EMPTY

sealed interface UiState<out T> {

	data object Loading : UiState<Nothing>

	data class Success<T>(
		val data: T
	) : UiState<T>

	data class Error(
		val message: String? = null
	) : UiState<Nothing>
}