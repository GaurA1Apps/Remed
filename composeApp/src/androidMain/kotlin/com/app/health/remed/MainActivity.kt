package com.app.health.remed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.app.health.remed.ui.screens.add_medicine.AddMedScreen
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineState
import com.app.health.remed.ui.screens.splash.SplashScreen
import com.app.health.remed.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.Transparent.toArgb(), Color.White.toArgb()),
        )
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxWidth(),
        ) {
            SplashScreen {
                
            }
        }
    }
}