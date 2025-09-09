package com.app.health.remed.ui.screens.add_medicine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.health.remed.core.ReminderManager
import com.app.health.remed.data.MedicineRepository
import com.app.health.remed.navigation.EventFlow
import com.app.health.remed.navigation.NavigationEvent
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineEvent
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineState
import com.app.health.remed.ui.screens.add_medicine.components.MedValidator
import com.app.health.remed.ui.screens.add_medicine.components.TimePickerEvent
import com.app.health.remed.ui.screens.add_medicine.mapper.toMedicineEntity
import com.app.health.remed.utils.MedicineType
import com.app.health.remed.utils.formatTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddMedicineViewModel(
    private val repository: MedicineRepository,
    private val reminderManager: ReminderManager
) : ViewModel() {

    private val _state = MutableStateFlow(AddMedicineState())
    val state = _state.asStateFlow()

    val navigationEvents = EventFlow<NavigationEvent>()
    private val medValidator = MedValidator()

    fun onEvent(event: AddMedicineEvent) {
        when (event) {
            is AddMedicineEvent.onAmountChanged -> {
                _state.update { it.copy(amount = event.amount.toString(), amountError = "") }
            }

            AddMedicineEvent.onBack -> {
                _state.update { it }
            }

            is AddMedicineEvent.onDoseChanged -> {
                _state.update { it.copy(dosage = event.dose, dosageError = "") }
            }

            is AddMedicineEvent.onNameChanged -> {
                _state.update { it.copy(name = event.name, nameError = "") }
            }

            AddMedicineEvent.onSave -> {
                val result = medValidator.validate(_state.value)

                if (!result.success) {
                    _state.value = _state.value.copy(
                        nameError = result.nameError.orEmpty(),
                        dosageError = result.dosageError.orEmpty(),
                        amountError = result.amountError.orEmpty(),
                        timeError = result.timeError.orEmpty()
                    )
                    return
                } else {
                    _state.value = _state.value.copy(
                        nameError = "",
                        dosageError = "",
                        amountError = "",
                        timeError = ""
                    )
                }

                saveMedicinetoDB()

                reminderManager.scheduleDailyReminder(medicineEntity = _state.value.toMedicineEntity())
            }

            is AddMedicineEvent.OnMedicineTypeSelected -> {
                _state.update {
                    it.copy(selectedMedicineType = event.medicineType)
                }
            }

            is AddMedicineEvent.OnDurationSelected -> {
                _state.update {
                    it.copy(selectedDuration = event.duration)
                }
            }
            is AddMedicineEvent.OnFrequencySelected -> {
                _state.update {
                    it.copy(selectedFrequency = event.frequency)
                }
            }

            is AddMedicineEvent.TimePicker -> {
                handleTimePickerEvent(event.event)
            }
        }
    }

    private fun saveMedicinetoDB() {
        val medicine = _state.value.toMedicineEntity()
        viewModelScope.launch {
            repository.insertMedicine(medicine)
            navigationEvents.send(NavigationEvent.GoBack)
        }
    }

    private fun handleTimePickerEvent(event: TimePickerEvent) {
        when (event) {
            is TimePickerEvent.OnTimeChanged -> {
                _state.update { s ->
                    s.copy(
                        timePickerState = s.timePickerState.copy(
                            hour = event.hour,
                            minute = event.minute
                        )
                    )
                }
            }

            TimePickerEvent.OnConfirmClick -> {
                val hour = _state.value.timePickerState.hour
                val minute = _state.value.timePickerState.minute
                _state.update { parentState ->
                    parentState.copy(
                        displayedTime = formatTime(hour, minute),
                        timePickerState = parentState.timePickerState.copy(
                            isVisible = false
                        ),
                        timeError = ""
                    )
                }
            }

            TimePickerEvent.OnDismiss -> {
                _state.update { parentState ->
                    parentState.copy(
                        timePickerState = parentState.timePickerState.copy(
                            isVisible = false
                        )
                    )
                }
            }

            TimePickerEvent.OnOpen -> {
                _state.update { parentState ->
                    parentState.copy(
                        timePickerState = parentState.timePickerState.copy(
                            isVisible = true
                        )
                    )
                }
            }
        }
    }
}