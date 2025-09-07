package com.app.health.remed.domain

import com.app.health.remed.utils.DoseStatus
import com.app.health.remed.utils.MedicineType

data class Medicine(
    val id: Int,
    val name: String,
    val type: MedicineType,
    val dosage: String,
    val amount: Int,
    val hour: Int,
    val minute: Int,
    val doseStatus: DoseStatus = DoseStatus.SCHEDULED
)

fun sampleMedicine() = Medicine(
    id = 1,
    name = "Paracetamol",
    type = MedicineType.PILL,
    dosage = "500mg",
    amount = 1,
    hour = 10,
    minute = 30
)
