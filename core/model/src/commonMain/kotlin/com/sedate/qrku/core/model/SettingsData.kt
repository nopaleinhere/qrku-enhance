package com.sedate.qrku.core.model

import androidx.compose.runtime.Stable
import com.sedate.qrku.core.common.constants.SeConst.EMPTY

@Stable
data class SettingsData(
	val isBeepEnabled: Boolean = true,
	val isVibrateEnabled: Boolean = true,
	val isAutoOpenEnabled: Boolean = false,
	val isConfirmBeforeOpenEnabled: Boolean = true,
	val appVersion: String = String.EMPTY
)