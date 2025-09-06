package com.app.health.remed.navigation.nav_graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.app.health.remed.navigation.Home
import com.app.health.remed.navigation.OnBoardingFinish
import com.app.health.remed.navigation.OnBoardingGraph
import com.app.health.remed.navigation.OnBoardingStart
import com.app.health.remed.ui.screens.onboarding.OnBoardingFinal
import com.app.health.remed.ui.screens.onboarding.OnBoardingScreen
import com.app.health.remed.ui.screens.onboarding.OnBoardingViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.onBoardingGraph(navController: NavHostController) {
    navigation<OnBoardingGraph>(startDestination = OnBoardingStart) {

        // Navigation destinations
        composable<OnBoardingStart> {
            val onBoardingViewModel = koinViewModel<OnBoardingViewModel>()
            OnBoardingScreen {
                onBoardingViewModel.onGetStartedClick()
                navController.navigate(OnBoardingFinish)
            }
        }

        composable<OnBoardingFinish> {
            OnBoardingFinal(
                onNavigate = {
                    navController.navigate(Home) {
                        popUpTo(OnBoardingGraph) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}