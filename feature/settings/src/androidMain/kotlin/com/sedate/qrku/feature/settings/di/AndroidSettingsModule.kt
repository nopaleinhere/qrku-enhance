package com.sedate.qrku.feature.settings.di

import com.sedate.qrku.feature.settings.AndroidAppInfoProvider
import com.sedate.qrku.feature.settings.AndroidSettingsNavigator
import com.sedate.qrku.feature.settings.contract.AppInfoProvider
import com.sedate.qrku.feature.settings.contract.SettingsNavigator
import org.koin.dsl.module

val androidSettingsModule = module {

	single<SettingsNavigator> {
		AndroidSettingsNavigator(get())
	}

	single<AppInfoProvider> {
		AndroidAppInfoProvider(get())
	}
}