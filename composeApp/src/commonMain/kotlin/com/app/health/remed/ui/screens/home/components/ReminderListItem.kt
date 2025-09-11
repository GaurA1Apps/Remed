package com.app.health.remed.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.health.remed.domain.models.Dose
import com.app.health.remed.domain.models.DoseWithMedicineDomain
import com.app.health.remed.domain.models.Medicine
import com.app.health.remed.ui.screens.home.getIcon
import com.app.health.remed.utils.displayTimeWithAMPM
import com.app.health.remed.utils.toCamelCase
import org.jetbrains.compose.resources.painterResource

@Composable
fun ReminderListItem(
    medicine: Medicine,
    dose: Dose
) {
    val title = remember { medicine.name.toCamelCase() }
    val subtitle = remember { "${medicine.amount} ${medicine.type.toString().toCamelCase()}" }

    ListItem(
        modifier = Modifier.padding(vertical = 12.dp),
        colors = ListItemDefaults.colors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        leadingContent = {
            Image(
                painter = painterResource(medicine.type.getIcon()),
                contentDescription = null,
                modifier = Modifier.padding(end = 16.dp).size(50.dp)
            )
        },
        headlineContent = {
            Text(
                modifier = Modifier.padding(bottom = 4.dp),
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        supportingContent = {
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            )
        },
        trailingContent = {
            Text(
                text = displayTimeWithAMPM(dose.time),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            )
        }
    )
    HorizontalDivider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
    )
}
