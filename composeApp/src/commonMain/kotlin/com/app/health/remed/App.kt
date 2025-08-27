package com.app.health.remed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.health.remed.navigation.AddMed
import com.app.health.remed.navigation.Detail
import com.app.health.remed.navigation.Home
import com.app.health.remed.navigation.OnBoarding
import com.app.health.remed.prefs.DatastoreRepository
import com.app.health.remed.prefs.rememberDatastoreRepository
import com.app.health.remed.ui.screens.add_medicine.AddMedScreen
import com.app.health.remed.ui.screens.home.HomeScreen
import com.app.health.remed.ui.screens.onboarding.OnBoardingScreen
import com.app.health.remed.ui.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    dataStore: DataStore<Preferences>
) {
    AppTheme {
        AppNavHost(dataStore)
    }
}

@Composable
fun AppNavHost(
    dataStore: DataStore<Preferences>
) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val datastoreRepository = rememberDatastoreRepository(dataStore)
    val isOnBoardingDone by datastoreRepository.isOnBoardingDone()
        .collectAsStateWithLifecycle(false)

    if (!isOnBoardingDone) {
        OnBoardingScreen {
            scope.launch {
                datastoreRepository.saveOnBoarding(value = true)
            }
        }
    } else {
        NavHost(
            navController = navController,
            startDestination = Home
        ) {
            // Navigation destinations

            composable<OnBoarding> {
                OnBoardingScreen {
                    scope.launch {
                        datastoreRepository.saveOnBoarding(value = true)
                        navController.navigate(Home)
                    }
                }
            }

            composable<Home> {
                HomeScreen(
                    onDetail = { navController.navigate(Detail) },
                    onAddMed = { navController.navigate(AddMed) }
                )
            }

            composable<AddMed> {
                AddMedScreen(
                    onBack = {
                        // Handle back navigation
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}