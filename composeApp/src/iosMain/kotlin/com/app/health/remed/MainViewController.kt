package com.app.health.remed

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.app.health.remed.prefs.createDataStore

fun MainViewController() = ComposeUIViewController {
    App(
        remember { createDataStore() }
    )
}