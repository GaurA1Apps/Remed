package com.app.health.remed

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.health.remed.navigation.AddMed
import com.app.health.remed.navigation.Detail
import com.app.health.remed.navigation.Home
import com.app.health.remed.ui.screens.add_medicine.AddMedScreen
import com.app.health.remed.ui.screens.home.HomeScreen
import com.app.health.remed.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    AppTheme {
        AppNavHost()
    }
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Home
    ) {
        // Navigation destinations
        composable<Home> {
            HomeScreen(
                onDetail = { navController.navigate(Detail) },
                onAddMed = { navController.navigate(AddMed) }
            )
        }

        composable<AddMed> {
            AddMedScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}