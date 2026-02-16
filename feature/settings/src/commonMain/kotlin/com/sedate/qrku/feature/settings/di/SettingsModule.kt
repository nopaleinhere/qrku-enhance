package com.sedate.qrku.feature.settings.di

import com.sedate.qrku.feature.settings.viewmodel.SettingsViewModel
import org.koin.dsl.module

val settingsModule = module {
	factory {
		SettingsViewModel(
			appSettingsPreference = get(),
			appInfoProvider = get(),
			settingsPreference = get(),
			navigator = get()
		)
	}
}