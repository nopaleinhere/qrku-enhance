package com.sedate.qrku.di

import com.sedate.qrku.core.database.di.databaseModule
import com.sedate.qrku.feature.history.di.androidHistoryModule
import com.sedate.qrku.feature.overview.di.androidOverviewModule
import com.sedate.qrku.feature.scan.di.androidScanModule
import org.koin.core.KoinApplication

actual fun KoinApplication.platformModule() {
	modules(
		androidScanModule,
		androidOverviewModule,
		androidHistoryModule,
		databaseModule
	)
}