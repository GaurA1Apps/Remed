package com.app.health.remed.ui.screens.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.health.remed.domain.Medicine
import com.app.health.remed.ui.theme.yellow
import com.app.health.remed.utils.formatTime
import com.app.health.remed.utils.toCamelCase
import org.jetbrains.compose.resources.painterResource
import remed.composeapp.generated.resources.Res
import remed.composeapp.generated.resources.ic_info

@Composable
fun ReminderListItem(
    medicine: Medicine,
) {
    val title = remember { medicine.name.toCamelCase() }
    val subtitle = remember { "${medicine.amount} ${medicine.type.name.toCamelCase()}" }
    val time = remember { formatTime(hour = medicine.hour, minute = medicine.minute) }

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 12.dp),
        border = BorderStroke(
            0.5.dp,
            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 0.5.dp)
    ) {
        ListItem(
            colors = ListItemDefaults.colors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            leadingContent = {
                Box(
                    modifier = Modifier
                        .clip(CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_info),
                        contentDescription = null,
                        tint = yellow,
                        modifier = Modifier.size(36.dp)
                    )
                }
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
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                    )
                )
            },
            trailingContent = {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.9f))                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = time,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }
        )
    }
}
