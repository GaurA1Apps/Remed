package com.app.health.remed.domain.models

import com.app.health.remed.utils.DoseStatus
import com.app.health.remed.utils.MedicineType

data class DoseWithMedicineDomain(
    val medicine: Medicine,
    val dosesList: List<Dose>,
)

