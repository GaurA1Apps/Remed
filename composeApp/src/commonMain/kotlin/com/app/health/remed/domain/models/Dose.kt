package com.app.health.remed.domain.models

import com.app.health.remed.utils.DoseStatus
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime

data class Dose(
    val id: Int,
    val medicineId: Int,
    val date: LocalDate,
    val time: LocalTime,
    val status: DoseStatus
)
