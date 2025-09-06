package com.app.health.remed.ui.screens.onboarding

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.app.health.remed.ui.screens.home.components.PrimaryButton
import com.app.health.remed.ui.theme.primaryLight
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.RequestCanceledException
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import dev.icerock.moko.permissions.notifications.RemoteNotificationPermission
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.option.viewModelScopeFactory
import remed.composeapp.generated.resources.Res
import remed.composeapp.generated.resources.ic_noti_bell

@Composable
fun OnBoardingFinal(
    onNavigate: () -> Unit
) {
    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val controller = factory.createPermissionsController()
    val scope = rememberCoroutineScope()

    BindEffect(controller)

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Notification Icon
            Box(
                modifier = Modifier
                    .padding(bottom = 32.dp)
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF4285F4)),
                // Google Blue
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_noti_bell),
                    contentDescription = "Notification Icon",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(90.dp)
                )
            }

            // "Welcome To"
            Text(
                text = "Enable Notifications",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFFF7043) // Orange
                ),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Subtitle
            Text(
                text = "To ensure you don't miss a dose,\n Please allow MediMinder to send you notifications.",
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
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PrimaryButton(
                    text = "Allow Notifications",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = {
                        scope.launch {
                            try {
                                controller.providePermission(RemoteNotificationPermission)
                                onNavigate.invoke()
                            } catch(deniedAlways: DeniedAlwaysException) {
                                onNavigate.invoke()
                            } catch(denied: DeniedException) {
                                onNavigate.invoke()
                            } catch (e: RequestCanceledException) {
                                e.printStackTrace()
                            }
                        }
                    }
                )
                TextButton(
                    onClick = {
                        onNavigate.invoke()
                    }
                ) {
                    Text(
                        text = "Skip for now",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun OnBoardingFinalPreview() {
    OnBoardingFinal(
        onNavigate = {}
    )
}