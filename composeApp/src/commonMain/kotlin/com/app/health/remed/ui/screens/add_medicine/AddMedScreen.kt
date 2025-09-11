package com.app.health.remed.ui.screens.add_medicine

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineEvent
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineState
import com.app.health.remed.ui.screens.add_medicine.components.grids.DurationGrid
import com.app.health.remed.ui.screens.add_medicine.components.grids.FrequencyGrid
import com.app.health.remed.ui.screens.add_medicine.components.grids.MedicineTypeGrid
import com.app.health.remed.ui.screens.add_medicine.components.grids.TimeSlots
import com.app.health.remed.ui.screens.add_medicine.components.picker.TimePickerDialog
import com.app.health.remed.ui.screens.add_medicine.components.textfield.AddScreenLabel
import com.app.health.remed.ui.screens.add_medicine.components.textfield.LabeledTextField
import com.app.health.remed.ui.screens.home.components.PrimaryButton
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedScreen(
    state: AddMedicineState,
    onEvent: (AddMedicineEvent) -> Unit
) {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    modifier = Modifier
                        .clickable { onEvent(AddMedicineEvent.onBack) }
                        .padding(end = 8.dp)
                        .size(24.dp),
                    tint = MaterialTheme.colorScheme.secondary,
                    contentDescription = "Go Back"
                )
                Text(
                    "Add Medicine",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.headlineMedium
                )
            }
        },
        bottomBar = {
            PrimaryButton(
                text = "Save",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(16.dp),
                onClick = {
                    onEvent(AddMedicineEvent.onSave)
                }
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    "Fill out the fields and hit the Save Button to add it!",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start
                )

                Spacer(Modifier.height(12.dp))

                LabeledTextField(
                    label = "Name*",
                    placeholder = "Medicine Name(e.g. Ibuprofen)",
                    value = state.name,
                    errorMessage = state.nameError,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Words,
                        keyboardType = KeyboardType.Text,
                        autoCorrectEnabled = true
                    ),
                    onValueChange = {
                        onEvent(AddMedicineEvent.onNameChanged(it))
                    }
                )

                Spacer(Modifier.height(8.dp))

                LabeledTextField(
                    label = "Dose*",
                    placeholder = "Dosage (e.g. 100mg)",
                    value = state.dosage,
                    errorMessage = state.dosageError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    onValueChange = {
                        onEvent(AddMedicineEvent.onDoseChanged(it))
                    }
                )

                Spacer(Modifier.height(8.dp))

                LabeledTextField(
                    label = "Amount*",
                    placeholder = "Amount (e.g. 2)",
                    value = state.amount,
                    errorMessage = state.amountError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = {
                        onEvent(AddMedicineEvent.onAmountChanged(it.toIntOrNull() ?: 1))
                    }
                )

                AddScreenLabel(text = "Medication Type")

                MedicineTypeGrid(
                    state = state,
                    onEvent = onEvent
                )

                AddScreenLabel(text = "How Often?")

                FrequencyGrid(
                    state = state,
                    onEvent = onEvent
                )

                AddScreenLabel(text = "For how Long?")

                DurationGrid(
                    state = state,
                    onEvent = onEvent
                )

                Spacer(Modifier.height(8.dp))

                AddScreenLabel(text = "Select Time Slots")

                Spacer(Modifier.height(8.dp))

                TimeSlots(
                    state = state,
                    onEvent = onEvent
                )

                // Active TimePicker
                state.activeSlotIndex?.let { index ->
                    val slot = state.timeSlots[index]
                    TimePickerDialog(
                        index = index,
                        state = slot,
                        onEvent = { event ->
                            onEvent(event)
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AddMedScreenPreview() {
    AddMedScreen(
        state = AddMedicineState(),
        onEvent = {}
    )
}