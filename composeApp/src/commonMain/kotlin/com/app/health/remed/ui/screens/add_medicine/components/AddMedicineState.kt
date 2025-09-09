package com.app.health.remed.ui.screens.add_medicine.components

import com.app.health.remed.utils.DoseStatus
import com.app.health.remed.utils.Duration
import com.app.health.remed.utils.Frequency
import com.app.health.remed.utils.MedicineType
import com.app.health.remed.utils.getCurrentTime

data class AddMedicineState(
    var name: String = "",
    var nameError: String = "",
    var dosage: String = "",
    var dosageError: String = "",
    var amount: String = "",
    var amountError: String = "",
    var displayedTime: String = "",
    var timeError: String = "",
    val selectedMedicineType: MedicineType?= null,
    val selectedFrequency: Frequency ?= null,
    val selectedDuration: Duration?= null,

    // Multiple slots
    val timeSlots: List<TimePickerState> = listOf(TimePickerState()),

    // Which slot is currently active for picking
    val activeSlotIndex: Int? = null,


    var doseStatus: DoseStatus = DoseStatus.SCHEDULED
)

data class TimePickerState(
    var isConfirmed: Boolean = false,
    val hour: Int = getCurrentTime().hour,
    val minute: Int = getCurrentTime().minute,
    var is24Hour: Boolean = false
) {
    fun formatted(): String {
        val h = if (is24Hour) hour else ((hour + 11) % 12 + 1)
        val m = minute.toString().padStart(2, '0')
        val suffix = if (is24Hour) "" else if (hour < 12) "AM" else "PM"
        return "$h:$m $suffix".trim()
    }
}
