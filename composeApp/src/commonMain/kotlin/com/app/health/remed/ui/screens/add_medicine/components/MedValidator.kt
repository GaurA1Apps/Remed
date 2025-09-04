package com.app.health.remed.ui.screens.add_medicine.components

class MedValidator {

    fun validate(state: AddMedicineState): ValidationResult {
        var success = true
        var nameError: String? = null
        var dosageError: String? = null
        var amountError: String? = null
        var timeError: String? = null

        if (state.name.isBlank()) {
            success = false
            nameError = "Name cannot be empty"
        }

        if (state.dosage.isBlank()) {
            success = false
            dosageError = "Please enter dosage for your medicine"
        }
        if (state.displayedTime.isBlank()) {
            success = false
            timeError = "Please select a medication time"
        }

        if (state.amount.isBlank()) {
            success = false
            amountError = "Amount cannot be empty"
        } else if (state.amount.toIntOrNull() == null || state.amount.toInt() <= 0) {
            success = false
            amountError = "Amount must be a valid positive number"
        }

        return ValidationResult(success, nameError, dosageError, amountError, timeError)
    }
}

data class ValidationResult(
    val success: Boolean,
    val nameError: String? = null,
    val dosageError: String? = null,
    val amountError: String? = null,
    val timeError: String? = null
)

