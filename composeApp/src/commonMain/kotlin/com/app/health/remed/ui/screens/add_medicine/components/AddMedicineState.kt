package com.app.health.remed.ui.screens.add_medicine.components

import com.app.health.remed.utils.DoseStatus
import com.app.health.remed.utils.MedicineType
import com.app.health.remed.utils.getCurrentTime

data class AddMedicineState(
    var name: String = "",
    var type: MedicineType = MedicineType.PILL,
    var dosage: String = "",
    var amount: String = "",
    var displayedTime: String = "",
    var timePickerState: TimePickerState = TimePickerState(),
    var doseStatus: DoseStatus = DoseStatus.SCHEDULED
)

data class TimePickerState(
    var isVisible: Boolean = false,
    val hour: Int = getCurrentTime().hour,
    val minute: Int = getCurrentTime().minute,
    var is24Hour: Boolean = false
)
