package com.sedate.qrku.core.datastore.di


import com.sedate.qrku.core.datastore.createDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformDataStoreModule: Module = module {
    single {
        createDataStore()
    }
}