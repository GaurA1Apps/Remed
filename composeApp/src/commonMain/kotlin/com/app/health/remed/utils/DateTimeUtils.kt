package com.app.health.remed.utils
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atDate
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.DateTimeFormatBuilder
import kotlinx.datetime.format.Padding
import kotlinx.datetime.format.char
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun LocalDateTime.Companion.now(timeZone: TimeZone = TimeZone.currentSystemDefault()) = Clock.System.now().toLocalDateTime(timeZone)

fun formatTime(hour: Int, minute: Int): String { return LocalTime(hour, minute).toString() }

fun displayTimeWithAMPM(time: LocalTime): String {
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


/**
 * Convert LocalDate -> start of day millis
 */
@OptIn(ExperimentalTime::class)
fun LocalDate.dateToEpochMillis(zone: TimeZone = TimeZone.currentSystemDefault()): Long =
    this.atStartOfDayIn(zone).toEpochMilliseconds()

/**
 * Convert LocalTime + LocalDate -> millis
 */
@OptIn(ExperimentalTime::class)
fun LocalTime.timeToEpochMillis(date: LocalDate, zone: TimeZone = TimeZone.currentSystemDefault()): Long = this.atDate(date).toEpochMilliseconds(zone)

@OptIn(ExperimentalTime::class)
fun LocalDateTime.toEpochMilliseconds(timeZone: TimeZone = TimeZone.currentSystemDefault()): Long {
    return this.toInstant(timeZone = timeZone).toEpochMilliseconds()
}

@OptIn(ExperimentalTime::class)
fun LocalDate.Companion.now(timeZone: TimeZone = TimeZone.currentSystemDefault()) = LocalDateTime.now(timeZone).date

