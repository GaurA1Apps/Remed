package com.app.health.remed.core

import com.app.health.remed.data.entity.MedicineEntity
import com.app.health.remed.utils.AppLogger
import com.app.health.remed.utils.getCurrentDate
import com.app.health.remed.utils.now
import com.tweener.alarmee.AlarmeeService
import com.tweener.alarmee.model.Alarmee
import com.tweener.alarmee.model.AndroidNotificationConfiguration
import com.tweener.alarmee.model.AndroidNotificationPriority
import com.tweener.alarmee.model.IosNotificationConfiguration
import com.tweener.alarmee.model.RepeatInterval
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime

class ReminderManager(
    private val alarmeeService: AlarmeeService
) {
    private val medicineService get() = alarmeeService.local

    fun scheduleDailyDose(
        medicineName: String,
        amount: String,
        type: String,
        time: LocalTime
    ) {
        val scheduledTime = LocalDateTime(
            date = LocalDate.now(),
            time = time
        )

        medicineService.schedule(
            alarmee = Alarmee(
                uuid = "daily_medicine_reminder_${medicineName}_${time.hour}_${time.minute}",
                notificationTitle = "💊 Time to take $medicineName",
                notificationBody = "${amount} ${type} of $medicineName",
                scheduledDateTime = scheduledTime,
                repeatInterval = RepeatInterval.Daily,
                deepLinkUri = "medicineapp://medicine/${medicineName}",
                androidNotificationConfiguration = AndroidNotificationConfiguration(
                    priority = AndroidNotificationPriority.HIGH,
                    channelId = "medicine_reminders",
                ),
                iosNotificationConfiguration = IosNotificationConfiguration(
                    soundFilename = "medicine_alarm.wav",
                    badge = 1
                ),
            )
        )
    }

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
                iosNotificationConfiguration = IosNotificationConfiguration(

                ),
            )
        )
    }
}