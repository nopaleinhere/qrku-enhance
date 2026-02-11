package com.sedate.qrku

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.sedate.qrku.di.initKoin
import com.sedate.qrku.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class QrkuApp : Application() {
	override fun onCreate() {
		super.onCreate()

		AndroidThreeTen.init(this)

		initKoin(
			appDeclaration = {
				androidLogger()
				androidContext(this@QrkuApp)
				platformModule()
			}
		)
	}
}