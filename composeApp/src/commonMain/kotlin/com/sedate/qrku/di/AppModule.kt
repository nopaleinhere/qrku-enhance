package com.sedate.qrku.di

import com.sedate.qrku.core.common.di.commonModule
import com.sedate.qrku.core.common.di.sharedModule
import com.sedate.qrku.core.datastore.di.dataStoreModule
import com.sedate.qrku.feature.write.di.writeModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
	appDeclaration()

	modules(
		commonModule,
		sharedModule,
		dataStoreModule,
		writeModule
	)
}