package com.app.health.remed.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.health.remed.ui.screens.home.components.PrimaryButton
import com.app.health.remed.ui.theme.primaryLight
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import remed.composeapp.generated.resources.Res
import remed.composeapp.generated.resources.ic_app_icon
import remed.composeapp.generated.resources.ic_launcher_general

@Composable
fun OnBoardingScreen(
    onGetStartedClick: () -> Unit // callback for navigation
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo / Illustration
        Image(
            painter = painterResource(Res.drawable.ic_launcher_general), // replace with your asset
            contentDescription = "MediMinder Logo",
            modifier = Modifier
                .size(200.dp)
                .background(shape = RoundedCornerShape(16.dp), color = Color.Transparent)
                .padding(bottom = 32.dp)
        )

        // "Welcome To"
        Text(
            text = "Welcome To",
            style = MaterialTheme.typography.headlineMedium.copy(
                color = Color(0xFFFF7043) // Orange
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // "MediMinder"
        Text(
            text = "MediMinder",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                color = primaryLight // Blue
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Subtitle
        Text(
            text = "Your personal assistant for managing\nyour medication schedule.",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.Gray
            ),
            textAlign = TextAlign.Center
        )
    }

    // Get Started Button
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        PrimaryButton(
            text = "Get Started",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 32.dp),
            onClick = onGetStartedClick
        )
    }
}

@Preview()
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingScreen {}
}