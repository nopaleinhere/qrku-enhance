package com.sedate.qrku.core.datastore.di

import com.sedate.qrku.core.datastore.preference.App.AppSettingsPreferences
import com.sedate.qrku.core.datastore.preference.App.AppSettingsPreferencesImpl
import com.sedate.qrku.core.datastore.preference.settings.SettingsPreference
import com.sedate.qrku.core.datastore.preference.settings.SettingsPreferenceImpl
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformDataStoreModule: Module

val dataStoreModule = module {
	includes(platformDataStoreModule)

	single { AppSettingsPreferencesImpl(get()) } bind AppSettingsPreferences::class

	single { SettingsPreferenceImpl(get()) } bind SettingsPreference::class
}