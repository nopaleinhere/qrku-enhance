package com.sedate.qrku.feature.write.di

import com.sedate.qrku.feature.write.viewmodel.GenerateViewModel
import com.sedate.qrku.feature.write.viewmodel.WriteViewModel
import org.koin.dsl.module

val writeModule = module {
	factory { WriteViewModel() }
	factory { GenerateViewModel() }
}