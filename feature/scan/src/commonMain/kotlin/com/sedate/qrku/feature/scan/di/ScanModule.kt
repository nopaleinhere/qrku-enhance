package com.sedate.qrku.feature.scan.di

import com.sedate.qrku.feature.scan.viewmodel.ScanViewModel
import org.koin.dsl.module

val scanModule = module {
	factory {
		ScanViewModel(get())
	}
}