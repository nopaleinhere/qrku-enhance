package com.sedate.qrku.core.datastore.preference.settings

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.sedate.qrku.core.common.state.SettingsUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

internal class SettingsPreferenceImpl(
	private val dataStore: DataStore<Preferences>
) : SettingsPreference {
	override val settingsFlow: Flow<SettingsUiState> =
		dataStore.data.map { prefs ->
			SettingsUiState(
				isBeepEnabled = prefs[KEY_BEEP] ?: true,
				isVibrateEnabled = prefs[KEY_VIBRATE] ?: true,
				isAutoOpenEnabled = prefs[KEY_AUTO_OPEN] ?: false,
				isConfirmBeforeOpenEnabled = prefs[KEY_CONFIRM_OPEN] ?: true,
			)
		}
			.distinctUntilChanged()

	override suspend fun setBeep(value: Boolean) {
		dataStore.edit { it[KEY_BEEP] = value }
	}

	override suspend fun setVibrate(value: Boolean) {
		dataStore.edit { it[KEY_VIBRATE] = value }
	}

	override suspend fun setAutoOpen(value: Boolean) {
		dataStore.edit { it[KEY_AUTO_OPEN] = value }
	}

	override suspend fun setConfirmOpen(value: Boolean) {
		dataStore.edit { it[KEY_CONFIRM_OPEN] = value }
	}

	companion object {
		val KEY_BEEP = booleanPreferencesKey("key_beep")
		val KEY_VIBRATE = booleanPreferencesKey("key_vibrate")
		val KEY_AUTO_OPEN = booleanPreferencesKey("key_auto_open")
		val KEY_CONFIRM_OPEN = booleanPreferencesKey("key_confirm_open")
	}
}