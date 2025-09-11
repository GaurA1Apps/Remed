package com.app.health.remed.ui.mappers

import com.app.health.remed.domain.models.Medicine
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineState
import com.app.health.remed.utils.Frequency
import com.app.health.remed.utils.MedicineType
import kotlinx.datetime.LocalTime
import kotlin.uuid.Uuid

fun AddMedicineState.toMedicine(): Medicine {
    return Medicine(
        id = 0,
        name = name,
        type = selectedMedicineType ?: MedicineType.PILL,
        dosage = dosage,
        amount = amount,
        frequency = selectedFrequency ?: Frequency.Once,
        duration = selectedDuration?.numberOfDays ?: 1,
        times = timeSlots.map { slot ->
            LocalTime(slot.hour, slot.minute)
        }
    )
}