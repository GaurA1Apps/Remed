package com.app.health.remed.core

import com.tweener.alarmee.AlarmeeService
import com.tweener.alarmee.model.Alarmee
import com.tweener.alarmee.model.AndroidNotificationConfiguration
import com.tweener.alarmee.model.AndroidNotificationPriority
import com.tweener.alarmee.model.IosNotificationConfiguration

class ReminderManager(
    private val alarmeeService: AlarmeeService
) {
    private val medicineService get() = alarmeeService.local

     fun immediateReminder() {
        medicineService.immediate(
            alarmee = Alarmee(
                uuid = "myAlarmId",
                notificationTitle = "🚀 Congratulations! You've pushed an Alarmee right now!",
                notificationBody = "This notification will be displayed right away",
                androidNotificationConfiguration = AndroidNotificationConfiguration(
                    // Required configuration for Android target only (this parameter is ignored on iOS)
                    priority = AndroidNotificationPriority.DEFAULT,
                    channelId = "medicine_reminders",
                ),
                iosNotificationConfiguration = IosNotificationConfiguration(),
            )
        )
    }
}