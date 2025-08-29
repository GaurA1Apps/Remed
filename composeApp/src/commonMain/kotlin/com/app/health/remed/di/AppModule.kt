package com.app.health.remed.di

import com.app.health.remed.prefs.DatastoreRepository
import com.app.health.remed.ui.screens.onboarding.OnBoardingViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    singleOf(::DatastoreRepository)
    viewModelOf(::OnBoardingViewModel)
}

