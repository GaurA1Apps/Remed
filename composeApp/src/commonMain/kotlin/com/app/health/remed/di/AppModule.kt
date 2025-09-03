package com.app.health.remed.di

import com.app.health.remed.data.MedicineRepository
import com.app.health.remed.data.dao.MedicineDao
import com.app.health.remed.data.db.MedicineDatabase
import com.app.health.remed.data.db.getRoomDatabase
import com.app.health.remed.prefs.DatastoreRepository
import com.app.health.remed.ui.screens.add_medicine.AddMedicineViewModel
import com.app.health.remed.ui.screens.home.HomeViewModel
import com.app.health.remed.ui.screens.onboarding.OnBoardingViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    singleOf(::DatastoreRepository)

    //Room DB
    singleOf(::getRoomDatabase)
    single { get<MedicineDatabase>().medicineDao() }
    singleOf(::MedicineRepository)

    //ViewModels
    viewModelOf(::OnBoardingViewModel)
    viewModelOf(::AddMedicineViewModel)
    viewModelOf(::HomeViewModel)
}

