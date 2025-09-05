package com.app.health.remed.core

import com.tweener.alarmee.configuration.AlarmeeIosPlatformConfiguration
import com.tweener.alarmee.configuration.AlarmeePlatformConfiguration

actual fun createAlarmeePlatformConfiguration(): AlarmeePlatformConfiguration {
    val platformConfiguration: AlarmeePlatformConfiguration = AlarmeeIosPlatformConfiguration
    return platformConfiguration
}