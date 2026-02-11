package com.sedate.qrku.core.common.di

import com.sedate.qrku.core.common.utils.AppDispatchers
import com.sedate.qrku.core.common.utils.AppVersion
import com.sedate.qrku.core.common.utils.AppVersionImpl
import org.koin.dsl.module

val commonModule = module {
    single<AppDispatchers> { AppDispatchers.Companion }
    single<AppVersion> { AppVersionImpl() }
}