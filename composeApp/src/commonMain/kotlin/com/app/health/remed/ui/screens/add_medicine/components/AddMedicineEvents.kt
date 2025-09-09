package com.app.health.remed.ui.screens.add_medicine.components

import com.app.health.remed.utils.Duration
import com.app.health.remed.utils.Frequency
import com.app.health.remed.utils.MedicineType


sealed class AddMedicineEvent {
    data class onNameChanged(val name: String) : AddMedicineEvent()
    data class onDoseChanged(val dose: String) : AddMedicineEvent()
    data class onAmountChanged(val amount: Int) : AddMedicineEvent()

    data class OnTimeSlotClicked(val index: Int) : AddMedicineEvent()
    data class OnTimePicked(val index: Int, val hour: Int, val minute: Int) : AddMedicineEvent()
    object DismissTimePicker : AddMedicineEvent()

    data class OnFrequencySelected(val frequency: Frequency) : AddMedicineEvent()

    data class OnDurationSelected(val duration: Duration) : AddMedicineEvent()

    data class OnMedicineTypeSelected(val medicineType: MedicineType) : AddMedicineEvent()

    object onSave : AddMedicineEvent()
    object onBack : AddMedicineEvent()
}
