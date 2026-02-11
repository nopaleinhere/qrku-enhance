package com.sedate.qrku.feature.scan.di

import com.sedate.qrku.feature.scan.contract.ScanController
import org.koin.dsl.module

val iosScanModule = module {
	factory {
		ScanController()
	}
}