package com.app.health.remed.ui.screens.add_medicine.components

import com.app.health.remed.utils.Duration
import com.app.health.remed.utils.Frequency
import com.app.health.remed.utils.MedicineType


sealed class AddMedicineEvent {
    data class onNameChanged(val name: String) : AddMedicineEvent()
    data class onDoseChanged(val dose: String) : AddMedicineEvent()
    data class onAmountChanged(val amount: Int) : AddMedicineEvent()

    data class TimePicker(val event: TimePickerEvent) : AddMedicineEvent()

    data class OnFrequencySelected(val frequency: Frequency) : AddMedicineEvent()

    data class OnDurationSelected(val duration: Duration) : AddMedicineEvent()

    data class OnMedicineTypeSelected(val medicineType: MedicineType) : AddMedicineEvent()

    object onSave : AddMedicineEvent()
    object onBack : AddMedicineEvent()
}

sealed class TimePickerEvent {
    data class OnTimeChanged(val hour: Int, val minute: Int) : TimePickerEvent()
    object OnOpen : TimePickerEvent()
    object OnConfirmClick : TimePickerEvent()
    object OnDismiss : TimePickerEvent()
}

