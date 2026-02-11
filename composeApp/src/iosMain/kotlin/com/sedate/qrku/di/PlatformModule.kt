package com.sedate.qrku.di

import com.sedate.qrku.feature.history.di.iosHistoryModule
import com.sedate.qrku.feature.overview.di.iosOverviewModule
import com.sedate.qrku.feature.scan.di.iosScanModule
import org.koin.core.KoinApplication

actual fun KoinApplication.platformModule() {
	modules(
		iosScanModule,
		iosOverviewModule,
		iosHistoryModule
	)
}