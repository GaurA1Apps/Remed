package com.app.health.remed.ui.screens.add_medicine

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.health.remed.data.MedicineRepository
import com.app.health.remed.data.entity.MedicineEntity
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineEvent
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineState
import com.app.health.remed.ui.screens.add_medicine.components.TimePickerEvent
import com.app.health.remed.utils.AppLogger
import com.app.health.remed.utils.DoseStatus
import com.app.health.remed.utils.MedicineType
import com.app.health.remed.utils.formatTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddMedicineViewModel(
    private val repository: MedicineRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AddMedicineState())
    val state = _state.asStateFlow()

    fun onEvent(event: AddMedicineEvent) {
        when (event) {
            is AddMedicineEvent.onAmountChanged -> {
                _state.update { it.copy(amount = event.amount.toString()) }
            }

            AddMedicineEvent.onBack -> {
                _state.update { it }
            }

            is AddMedicineEvent.onDoseChanged -> {
                _state.update { it.copy(dosage = event.dose) }
            }

            is AddMedicineEvent.onNameChanged -> {
                _state.update { it.copy(name = event.name) }
            }

            AddMedicineEvent.onSave -> {
                val medicine = MedicineEntity(
                    name = _state.value.name,
                    dosage = _state.value.dosage,
                    amount = _state.value.amount.toInt(),
                    type = _state.value.type,
                    hour = _state.value.timePickerState.hour,
                    minute = _state.value.timePickerState.minute,
                    doseStatus = DoseStatus.SCHEDULED
                )
                viewModelScope.launch {
                    repository.insertMedicine(medicine)
                }
            }

            is AddMedicineEvent.onTypeChanged -> {
                _state.update { it.copy(type = MedicineType.valueOf(event.type)) }
            }

            is AddMedicineEvent.TimePicker -> {
                handleTimePickerEvent(event.event)
            }
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
                        displayedTime = formatTime(hour, minute)
                    )
                }
                _state.update { parentState ->
                    parentState.copy(
                        timePickerState = parentState.timePickerState.copy(
                            isVisible = false
                        )
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