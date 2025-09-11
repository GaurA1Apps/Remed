package com.app.health.remed.ui.screens.home.components

// --- Imports ---
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import remed.composeapp.generated.resources.Res
import remed.composeapp.generated.resources.ic_home_calendar


@Composable
fun EmptyHomeScreen(
    onAddMedicineClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Top Image
        Image(
            painter = painterResource( Res.drawable.ic_home_calendar), // replace with your drawable
            contentDescription = "Medicine Calendar",
            modifier = Modifier
                .size(160.dp)
                .padding(bottom = 24.dp)
        )

        // Title
        Text(
            text = "Manage your meds",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineMedium, // Blue color
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Subtitle
        Text(
            text = "Add your meds to be reminded on time and track your health",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Add Medicine Button
        PrimaryButton(
            text = "Add medicine",
            onClick = onAddMedicineClick,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
    }
}

// --- Reusable Button ---
@Composable
fun PrimaryButton(
    text: String,
    color: Color = Color(0xFFFFC107),
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color, // Yellow background
            contentColor = Color.White
        )
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Preview
@Composable
fun PreviewEmptyHomeScreen() {
    EmptyHomeScreen {
        // Preview code here
    }
}

