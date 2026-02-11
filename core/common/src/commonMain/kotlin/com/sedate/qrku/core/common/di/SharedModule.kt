package com.sedate.qrku.core.common.di

import com.sedate.qrku.core.common.shared.SharedScope
import org.koin.dsl.module

val sharedModule = module {
	single {
		SharedScope()
	}
}