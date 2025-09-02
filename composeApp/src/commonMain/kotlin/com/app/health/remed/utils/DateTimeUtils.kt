package com.app.health.remed.utils

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