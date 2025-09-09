package com.app.health.remed.utils

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.DateTimeFormatBuilder
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

fun formatTime(hour: Int, minute: Int): String { return LocalTime(hour, minute).toString() }

fun displayTimeWithAMPM(hour: Int, minute: Int): String {
    val time = LocalTime(hour, minute)
    val formatter = LocalTime.Format {
        hour(padding = Padding.NONE)
        char(':')
        minute()
        char(' ')
        amPmMarker(am = "AM", pm = "PM")
    }
    return time.format(formatter)
}

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
