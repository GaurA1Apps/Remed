package com.app.health.remed.core

import android.app.NotificationManager
import androidx.compose.ui.graphics.Color
import com.app.health.remed.R
import com.tweener.alarmee.channel.AlarmeeNotificationChannel
import com.tweener.alarmee.configuration.AlarmeeAndroidPlatformConfiguration
import com.tweener.alarmee.configuration.AlarmeePlatformConfiguration

actual fun createAlarmeePlatformConfiguration(): AlarmeePlatformConfiguration =
    AlarmeeAndroidPlatformConfiguration(
        notificationIconResId = R.drawable.ic_launcher_general, // Your app's notification icon
        notificationChannels = listOf(
            AlarmeeNotificationChannel(
                id = "medicine_reminders",
                name = "Medicine Reminders",
                importance = NotificationManager.IMPORTANCE_HIGH,
                soundFilename = "medicine_alarm", // Optional custom sound
            )
        )
    )