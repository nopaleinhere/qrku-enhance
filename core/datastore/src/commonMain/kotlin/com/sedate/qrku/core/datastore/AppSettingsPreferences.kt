package com.sedate.qrku.core.datastore

import com.sedate.qrku.core.model.AppLanguage
import com.sedate.qrku.core.model.SeTheme
import kotlinx.coroutines.flow.Flow

interface AppSettingsPreferences {
	fun getAppLanguageCode(): Flow<String>
	suspend fun setAppLanguageCode(language: AppLanguage)
	fun getSeTheme(): Flow<String>
	suspend fun setSeTheme(theme: SeTheme)
}