package com.sedate.qrku.core.database.di

import com.sedate.qrku.core.database.AppDatabase
import com.sedate.qrku.core.database.DatabaseFactory
import com.sedate.qrku.core.database.repository.BarcodeRepository
import com.sedate.qrku.core.database.repository.BarcodeRepositoryImpl
import org.koin.dsl.module

val databaseModule = module {
	single {
		DatabaseFactory(
			context = get()
		).create()
	}

	single {
		get<AppDatabase>().barcodeHistoryDao()
	}

	single<BarcodeRepository> {
		BarcodeRepositoryImpl(
			dao = get()
		)
	}
}