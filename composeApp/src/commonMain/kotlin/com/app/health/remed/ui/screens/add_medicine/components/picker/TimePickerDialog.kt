package com.app.health.remed.ui.screens.add_medicine.components.picker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePickerLayoutType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.app.health.remed.ui.screens.add_medicine.components.TimePickerEvent
import com.app.health.remed.ui.screens.add_medicine.components.TimePickerState
import com.mohamedrejeb.calf.ui.timepicker.AdaptiveTimePicker
import com.mohamedrejeb.calf.ui.timepicker.rememberAdaptiveTimePickerState
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    state: TimePickerState,
    onEvent: (TimePickerEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    // Time Picker
    val pickerState = rememberAdaptiveTimePickerState(
        initialHour = state.hour,
        initialMinute = state.minute,
        is24Hour = state.is24Hour
    )


    // PickerState → ViewModel
    LaunchedEffect(pickerState.hour, pickerState.minute) {
        onEvent(TimePickerEvent.OnTimeChanged(pickerState.hour, pickerState.minute))
    }


    Dialog(
        onDismissRequest = { onEvent(TimePickerEvent.OnDismiss) }
    ) {
        Box(
            modifier = Modifier.fillMaxWidth()
                .wrapContentHeight()
                .background(
                    MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp, horizontal = 16.dp)
            ) {
                // Title
                Text(
                    text = "Select time",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.Start)
                )

                AdaptiveTimePicker(
                    state = pickerState,
                    modifier = Modifier.fillMaxWidth(),
                    layoutType = TimePickerLayoutType.Vertical,
                )

                Spacer(Modifier.height(24.dp))

                // Buttons Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { onEvent(TimePickerEvent.OnDismiss) }
                    ) {
                        Text("Cancel", color = MaterialTheme.colorScheme.error)
                    }
                    TextButton(
                        onClick = { onEvent(TimePickerEvent.OnConfirmClick) }
                    ) {
                        Text("Confirm", color = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TimePickerDialogPreview() {
    TimePickerDialog(
        state = TimePickerState(),
        onEvent = {}
    )
}