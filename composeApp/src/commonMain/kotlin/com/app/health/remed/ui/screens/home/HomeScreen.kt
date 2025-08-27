package com.app.health.remed.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.app.health.remed.ui.screens.home.components.EmptyHomeScreen

@Composable
fun HomeScreen(
    onDetail: (String) -> Unit,
    onAddMed: () -> Unit
) {
    EmptyHomeScreen {
        onAddMed.invoke()
    }
}