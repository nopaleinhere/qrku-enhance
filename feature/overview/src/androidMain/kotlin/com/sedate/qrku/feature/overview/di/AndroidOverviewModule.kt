package com.sedate.qrku.feature.overview.di

import com.sedate.qrku.feature.overview.viewmodel.QrCaptureViewModel
import org.koin.dsl.module

val androidOverviewModule = module {
	factory {
		QrCaptureViewModel(
			repository = get(),
			settingsPreference = get()
		)
	}
}