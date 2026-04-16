package com.sedate.qrku.core.datastore.preference.settings

import com.sedate.qrku.core.model.SettingsData
import kotlinx.coroutines.flow.Flow

interface SettingsPreference {
	val settingsFlow: Flow<SettingsData>
	suspend fun setBeep(value: Boolean)

	suspend fun setVibrate(value: Boolean)

	suspend fun setAutoOpen(value: Boolean)

	suspend fun setConfirmOpen(value: Boolean)
}
