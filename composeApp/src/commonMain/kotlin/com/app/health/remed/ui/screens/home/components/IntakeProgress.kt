package com.app.health.remed.ui.screens.home.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import remed.composeapp.generated.resources.Res
import remed.composeapp.generated.resources.ic_info
import remed.composeapp.generated.resources.ic_pill

@Composable
fun IntakeProgress(
    total: Int,
    taken: Int,
    day: String,
    modifier: Modifier = Modifier
) {
    val targetProgress = if (total > 0) taken.toFloat() / total.toFloat() else 0f

    // Animate the progress smoothly
    val animatedProgress by animateFloatAsState(
        targetValue = targetProgress,
        animationSpec = tween(
            durationMillis = 1000, // 1 second
            easing = FastOutSlowInEasing
        ),
        label = "ProgressAnimation"
    )

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*Text(
            text = "Intakes",
            style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.primary
        )*/

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(220.dp)
        ) {
            // Background arc
            CircularProgressIndicator(
                progress = 1f,
                strokeWidth = 16.dp,
                color = MaterialTheme.colorScheme.surfaceVariant,
                modifier = Modifier.fillMaxSize()
            )

            // Foreground progress arc (animated)
            CircularProgressIndicator(
                progress = animatedProgress,
                strokeWidth = 16.dp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.fillMaxSize()
            )

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Medicine image placeholder
                Image(
                    painter = painterResource(resource = Res.drawable.ic_pill), // replace with your image
                    contentDescription = "Medicine",
                    modifier = Modifier.size(64.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Taken / Total
                Text(
                    text = "$taken/$total",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary
                )

                // Day text
                Text(
                    text = day,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }
        }
    }
}
