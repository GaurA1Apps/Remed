package com.app.health.remed.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun formatTime(hour: Int, minute: Int): String { return LocalTime(hour, minute).toString() }

@OptIn(ExperimentalTime::class)
fun getCurrentTime(): LocalTime {
    val now = Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .time
    return now
}

fun getCurrentDay(): String {
    return getCurrentDate().dayOfWeek.name.toCamelCase()
}

@OptIn(ExperimentalTime::class)
fun getCurrentDate(): LocalDate {
    return Clock.System.now()
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .date
}

fun String.toCamelCase(): String {
    return lowercase().replaceFirstChar { it.titlecase() }
}
