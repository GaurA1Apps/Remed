package com.app.health.remed.ui.screens.add_medicine.mapper

import com.app.health.remed.data.entity.MedicineEntity
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineState
import com.app.health.remed.utils.DoseStatus

fun AddMedicineState.toMedicineEntity(): MedicineEntity {
    return MedicineEntity(
        name = name.trim(),
        dosage = dosage.trim(),
        amount = amount.toIntOrNull() ?: 0, // safer than direct toInt()
        type = type,
        hour = timePickerState.hour,
        minute = timePickerState.minute,
        doseStatus = DoseStatus.SCHEDULED
    )
}