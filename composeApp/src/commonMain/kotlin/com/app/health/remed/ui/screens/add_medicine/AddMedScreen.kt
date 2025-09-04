package com.app.health.remed.ui.screens.add_medicine

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.app.health.remed.ui.screens.add_medicine.components.picker.TimePickerDialog
import com.app.health.remed.ui.screens.add_medicine.components.picker.TimePickerField
import com.app.health.remed.ui.screens.add_medicine.components.textfield.LabeledDropdown
import com.app.health.remed.ui.screens.add_medicine.components.textfield.LabeledTextField
import com.app.health.remed.ui.screens.home.components.PrimaryButton
import com.app.health.remed.utils.MedicineType
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMedScreen(
    state: AddMedicineState,
    onEvent: (AddMedicineEvent) -> Unit
) {
    Scaffold(
        topBar = {
            Text(
                "Add Medicine",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(16.dp)
            )
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
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding),
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

                Spacer(Modifier.height(16.dp))

                LabeledTextField(
                    label = "Name*",
                    placeholder = "Name (e.g. Ibuprofen)",
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

                Spacer(Modifier.height(12.dp))

                LabeledDropdown(
                    label = "Type*",
                    options = MedicineType.entries.map { it.name },
                    selectedOption = state.type.name,
                    onOptionSelected = {
                        onEvent(AddMedicineEvent.onTypeChanged(it))
                    }
                )

                Spacer(Modifier.height(18.dp))

                LabeledTextField(
                    label = "Dose*",
                    placeholder = "Dose (e.g. 100mg)",
                    value = state.dosage,
                    errorMessage = state.dosageError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    onValueChange = {
                        onEvent(AddMedicineEvent.onDoseChanged(it))
                    }
                )

                Spacer(Modifier.height(12.dp))

                LabeledTextField(
                    label = "Amount*",
                    placeholder = "Dose (e.g. 3)",
                    value = state.amount,
                    errorMessage = state.amountError,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    onValueChange = {
                        onEvent(AddMedicineEvent.onAmountChanged(it.toIntOrNull() ?: 1))
                    }
                )

                Spacer(Modifier.height(16.dp))

                Text("Reminders", style = MaterialTheme.typography.titleMedium)

                Spacer(Modifier.height(8.dp))

                TimePickerField(
                    value = state.displayedTime,
                    placeholder = "Select Time",
                    errorMessage = state.timeError,
                    onEvent = {
                        onEvent(AddMedicineEvent.TimePicker(it))
                    },
                )
            }

            // Centered time picker in a Dialog
            if (state.timePickerState.isVisible) {
                TimePickerDialog(
                    state = state.timePickerState,
                    onEvent = {
                        onEvent(AddMedicineEvent.TimePicker(it))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
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