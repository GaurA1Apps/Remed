package com.app.health.remed

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.health.remed.di.createKoinConfiguration
import com.app.health.remed.navigation.AddMed
import com.app.health.remed.navigation.Detail
import com.app.health.remed.navigation.Home
import com.app.health.remed.navigation.NavigationEvent
import com.app.health.remed.navigation.OnBoarding
import com.app.health.remed.ui.screens.add_medicine.AddMedScreen
import com.app.health.remed.ui.screens.add_medicine.AddMedicineViewModel
import com.app.health.remed.ui.screens.home.HomeScreen
import com.app.health.remed.ui.screens.home.HomeViewModel
import com.app.health.remed.ui.screens.onboarding.OnBoardingScreen
import com.app.health.remed.ui.screens.onboarding.OnBoardingViewModel
import com.app.health.remed.ui.theme.AppTheme
import com.tweener.alarmee.rememberAlarmeeService
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinMultiplatformApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    KoinMultiplatformApplication(
        config = createKoinConfiguration()
    ) {
        AppTheme {
            Scaffold { innerPadding ->
                AppNavHost(innerPadding)
            }
        }
    }
}

@Composable
fun AppNavHost(
    innerPadding: PaddingValues
) {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier
            .padding(innerPadding)
            .consumeWindowInsets (innerPadding),
        navController = navController,
        startDestination = OnBoarding
    ) {
        // Navigation destinations
        composable<OnBoarding> {
            val onBoardingViewModel = koinViewModel<OnBoardingViewModel>()
            OnBoardingScreen {
                onBoardingViewModel.onGetStartedClick()
                navController.navigate(Home)
            }
        }

        composable<Home> {
            val homeViewModel = koinViewModel<HomeViewModel>()
            val list by homeViewModel.medicinesList.collectAsStateWithLifecycle()
            HomeScreen(
                list = list,
                onDetail = { navController.navigate(Detail) },
                onAddMed = { navController.navigate(AddMed) }
            )
        }

        composable<AddMed> {
            val addMedViewModel = koinViewModel<AddMedicineViewModel>()
            val state by addMedViewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                addMedViewModel.navigationEvents.flow.collect { event ->
                    when (event) {
                        is NavigationEvent.GoToDetails -> navController.navigate(Detail)
                        NavigationEvent.GoBack -> navController.popBackStack()
                    }
                }
            }

            AddMedScreen(
                state = state,
                onEvent = {
                    addMedViewModel.onEvent(it)
                }
            )
        }
    }
}