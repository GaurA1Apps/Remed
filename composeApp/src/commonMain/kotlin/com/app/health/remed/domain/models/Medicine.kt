package com.app.health.remed.domain.models

import com.app.health.remed.utils.Frequency
import com.app.health.remed.utils.MedicineType
import kotlinx.datetime.LocalTime

data class Medicine(
    val id: Int,
    val name: String,
    val type: MedicineType,
    val dosage: String,
    val amount: String,
    val frequency: Frequency,
    val duration: Int,
    val times: List<LocalTime>
)