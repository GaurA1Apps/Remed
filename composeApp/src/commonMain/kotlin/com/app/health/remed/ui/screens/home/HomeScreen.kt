package com.app.health.remed.ui.screens.home

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.app.health.remed.ui.screens.home.components.EmptyHomeScreen
import com.app.health.remed.ui.topbar.AppTopBar

@Composable
fun HomeScreen(
    onDetail: (String) -> Unit,
    onAddMed: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar()
        }
    ) {
        EmptyHomeScreen {
            onAddMed.invoke()
        }
    }
}