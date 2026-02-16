package com.sedate.qrku.core.datastore.preference.App

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.sedate.qrku.core.model.AppLanguage
import com.sedate.qrku.core.model.SeTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class AppSettingsPreferencesImpl(
	private val dataStore: DataStore<Preferences>
) : AppSettingsPreferences {

	private object PreferenceKeys {
		val LANGUAGE = stringPreferencesKey("language")
		val THEME = stringPreferencesKey("theme")
	}

	override fun getAppLanguageCode(): Flow<String> {
		return dataStore.data.map { preferences ->
			preferences[PreferenceKeys.LANGUAGE] ?: AppLanguage.ENGLISH.code
		}
	}

	override suspend fun setAppLanguageCode(language: AppLanguage) {
		dataStore.edit { preferences ->
			preferences[PreferenceKeys.LANGUAGE] = language.code
		}
	}

	override fun getSeTheme(): Flow<String> {
		return dataStore.data.map { preferences ->
			preferences[PreferenceKeys.THEME] ?: SeTheme.SYSTEM.value
		}
	}

	override suspend fun setSeTheme(theme: SeTheme) {
		dataStore.edit { preferences ->
			preferences[PreferenceKeys.THEME] = theme.value
		}
	}
}