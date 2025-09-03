package com.app.health.remed.di

import com.app.health.remed.data.db.getDatabaseBuilder
import com.app.health.remed.prefs.createDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        single { createDataStore(androidContext()) }
        single { getDatabaseBuilder(androidContext()) }
    }