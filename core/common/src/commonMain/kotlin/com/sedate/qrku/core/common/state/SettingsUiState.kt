package com.sedate.qrku.core.common.state

data class SettingsUiState(
	val isBeepEnabled: Boolean = true,
	val isVibrateEnabled: Boolean = true,
	val isAutoOpenEnabled: Boolean = false,
	val isConfirmBeforeOpenEnabled: Boolean = true,
	val appVersion: String = "0.0.1"
)