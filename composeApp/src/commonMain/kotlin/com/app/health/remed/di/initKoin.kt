package com.app.health.remed.di

import org.koin.dsl.KoinConfiguration

fun createKoinConfiguration(): KoinConfiguration {
    return KoinConfiguration {
        modules(platformModule,sharedModule)
    }
}