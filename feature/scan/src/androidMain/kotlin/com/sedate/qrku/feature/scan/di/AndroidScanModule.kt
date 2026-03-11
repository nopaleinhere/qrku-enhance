package com.sedate.qrku.feature.scan.di

import com.sedate.qrku.feature.scan.contract.ScanController
import com.sedate.qrku.feature.scan.viewmodel.ScanViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidScanModule = module {
	factory {
		ScanController(
			context = androidContext()
		)
	}
}