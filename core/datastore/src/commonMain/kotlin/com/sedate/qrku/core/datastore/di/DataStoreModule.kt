package com.sedate.qrku.core.datastore.di

import com.sedate.qrku.core.datastore.AppSettingsPreferences
import com.sedate.qrku.core.datastore.AppSettingsPreferencesImpl
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformDataStoreModule: Module

val dataStoreModule = module {
    includes(platformDataStoreModule)

    single { AppSettingsPreferencesImpl(get()) } bind AppSettingsPreferences::class
}