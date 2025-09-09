package com.app.health.remed.domain

import com.app.health.remed.data.entity.MedicineEntity
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineState
import com.app.health.remed.utils.DoseStatus
import com.app.health.remed.utils.MedicineType

fun AddMedicineState.toMedicine(): Medicine {
    return Medicine(
        id = 1,
        name = name.trim(),
        dosage = dosage.trim(),
        amount = amount.toIntOrNull() ?: 0,
        type = selectedMedicineType ?: MedicineType.PILL,
        hour = timePickerState.hour,
        minute = timePickerState.minute,
        doseStatus = DoseStatus.SCHEDULED
    )
}

fun MedicineEntity.toMedicine(): Medicine {
    return Medicine(
        id = id,
        name = name,
        dosage = dosage,
        amount = amount,
        type = type,
        hour = hour,
        minute = minute,
        doseStatus = doseStatus
    )
}

fun Medicine.toMedicineEntity(): MedicineEntity {
    return MedicineEntity(
        name = name,
        dosage = dosage,
        amount = amount,
        type = type,
        hour = hour,
        minute = minute,
        doseStatus = doseStatus
    )
}