package com.app.health.remed.ui.screens.add_medicine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.health.remed.domain.usecases.AddMedicineUseCase
import com.app.health.remed.navigation.EventFlow
import com.app.health.remed.navigation.NavigationEvent
import com.app.health.remed.ui.mappers.toMedicine
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineEvent
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineState
import com.app.health.remed.ui.screens.add_medicine.components.MedValidator
import com.app.health.remed.ui.screens.add_medicine.components.TimePickerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddMedicineViewModel(
    private val addMedicineUseCase: AddMedicineUseCase
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
                viewModelScope.launch {
                    navigationEvents.send(NavigationEvent.GoBack)
                }
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
                    )
                    return
                } else {
                    _state.value = _state.value.copy(
                        nameError = "",
                        dosageError = "",
                        amountError = "",
                    )
                }
                viewModelScope.launch {
                    addMedicineUseCase.invoke(state.value.toMedicine())
                    navigationEvents.send(NavigationEvent.GoBack)
                }
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
                val newSlots = List(event.frequency.slots) { TimePickerState() }
                _state.update { it.copy(selectedFrequency = event.frequency, timeSlots = newSlots) }
            }

            is AddMedicineEvent.OnTimeSlotClicked -> {
                _state.update { it.copy(activeSlotIndex = event.index) }
            }

            is AddMedicineEvent.OnTimePicked -> {
                _state.update { state ->
                    val updatedSlots = state.timeSlots.toMutableList()
                    updatedSlots[event.index] = updatedSlots[event.index].copy(
                        hour = event.hour,
                        minute = event.minute,
                        isConfirmed = true
                    )
                    state.copy(timeSlots = updatedSlots, activeSlotIndex = null)
                }
            }

            AddMedicineEvent.DismissTimePicker -> {
                _state.update { it.copy(activeSlotIndex = null) }
            }
        }
    }
}


    /*is TimePickerEvent.OnTimeChanged -> {
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
    }*/