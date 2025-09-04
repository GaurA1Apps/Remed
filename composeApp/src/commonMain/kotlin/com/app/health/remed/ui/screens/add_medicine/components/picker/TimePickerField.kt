package com.app.health.remed.ui.screens.add_medicine.components.picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.health.remed.ui.screens.add_medicine.components.TimePickerEvent
import com.app.health.remed.ui.screens.add_medicine.components.textfield.ErrorInfo

@Composable
fun TimePickerField(
    value: String,
    placeholder: String,
    onEvent: (TimePickerEvent) -> Unit,
    errorMessage: String? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
    ) {
        Column (
            modifier = Modifier.fillMaxWidth()
        ){
            OutlinedTextField(
                value = value,
                onValueChange = {}, // No typing allowed
                placeholder = {
                    Text(
                        placeholder,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                modifier = modifier
                    .fillMaxWidth()
                    .clickable {
                        onEvent(TimePickerEvent.OnOpen)
                    },
                singleLine = true,
                readOnly = true, // prevent keyboard
                enabled = false, // prevent direct edit but keep visual style
                colors = OutlinedTextFieldDefaults.colors(
                    disabledTextColor = LocalContentColor.current,
                    disabledBorderColor = MaterialTheme.colorScheme.outline,
                    disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    disabledPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (errorMessage.isNullOrEmpty().not()) {
                ErrorInfo(errorMessage)
            }
        }
    }
}
