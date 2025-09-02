package com.app.health.remed.ui.screens.add_medicine.components

import com.app.health.remed.utils.MedicineType


sealed class AddMedicineEvent {
    data class onNameChanged(val name: String) : AddMedicineEvent()
    data class onTypeChanged(val type: String) : AddMedicineEvent()
    data class onDoseChanged(val dose: String) : AddMedicineEvent()
    data class onAmountChanged(val amount: String) : AddMedicineEvent()

    data class TimePicker(val event: TimePickerEvent) : AddMedicineEvent()

    object onSave : AddMedicineEvent()
    object onBack : AddMedicineEvent()
}

sealed class TimePickerEvent {
    data class OnTimeChanged(val hour: Int, val minute: Int) : TimePickerEvent()
    object OnOpen : TimePickerEvent()
    object OnConfirmClick : TimePickerEvent()
    object OnDismiss : TimePickerEvent()
}

