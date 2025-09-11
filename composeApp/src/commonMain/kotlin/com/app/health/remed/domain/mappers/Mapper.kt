package com.app.health.remed.domain.mappers

import com.app.health.remed.data.entity.MedicineEntity
import com.app.health.remed.domain.models.Medicine
import com.app.health.remed.utils.now
import com.app.health.remed.utils.timeToEpochMillis
import kotlinx.datetime.LocalDate


fun Medicine.toEntity(): MedicineEntity {
    return MedicineEntity(
        id = id,
        name = name,
        type = type,
        dosage = dosage,
        amount = amount.toIntOrNull() ?: 0, // String → Int
        frequency = frequency.label,
        durationInDays = duration,
        times = times.map { localTime ->
            localTime.timeToEpochMillis(LocalDate.now())
        }
    )
}