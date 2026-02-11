package com.sedate.qrku.feature.history.di

import com.sedate.qrku.feature.history.viewmodel.HistoryViewModel
import org.koin.dsl.module

val androidHistoryModule = module {
	factory {
		HistoryViewModel(
			repository = get()
		)
	}
}