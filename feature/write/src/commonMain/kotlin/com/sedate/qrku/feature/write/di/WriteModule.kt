package com.sedate.qrku.feature.write.di

import com.sedate.qrku.feature.write.viewmodel.GenerateViewModel
import com.sedate.qrku.feature.write.viewmodel.LandingViewModel
import org.koin.dsl.module

val writeModule = module {
	factory { LandingViewModel() }
	factory { GenerateViewModel() }
}