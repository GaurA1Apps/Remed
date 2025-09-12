package com.app.health.remed.data.mappers

import com.app.health.remed.data.DoseWithMedicine
import com.app.health.remed.data.entity.DoseEntity
import com.app.health.remed.data.entity.MedicineEntity
import com.app.health.remed.domain.models.Dose
import com.app.health.remed.domain.models.DoseWithMedicineDomain
import com.app.health.remed.domain.models.Medicine
import com.app.health.remed.utils.Frequency
import com.app.health.remed.utils.displayTimeWithAMPM
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

fun DoseWithMedicine.toDomain(): List<DoseWithMedicineDomain> {
    val medicine = medicineEntity.toDomain()
    val doses = doseEntity.sortedBy { it.time }.map { it.toDomain() }
    return doses.map { dose ->
        DoseWithMedicineDomain(
            medicine = medicine,
            dosesList = listOf(dose)
        )
    }
}

@OptIn(ExperimentalTime::class)
fun MedicineEntity.toDomain() = Medicine(
    id = id,
    name = name,
    type = type,
    dosage = dosage,
    amount = amount.toString(),
    frequency = Frequency.fromString(frequency),
    duration = durationInDays,
    times = times.map { millis ->
        Instant.fromEpochMilliseconds(millis)
        .toLocalDateTime(TimeZone.currentSystemDefault())
            .time
    }
)

@OptIn(ExperimentalTime::class)
fun DoseEntity.toDomain() = Dose(
    id = id,
    medicineId = medicineId,
    date = Instant.fromEpochMilliseconds(date).toLocalDateTime(TimeZone.currentSystemDefault()).date,
    time = Instant.fromEpochMilliseconds(time).toLocalDateTime(TimeZone.currentSystemDefault()).time,
    status = status
)
