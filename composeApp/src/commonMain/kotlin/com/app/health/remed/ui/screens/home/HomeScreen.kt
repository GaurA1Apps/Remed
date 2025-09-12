package com.app.health.remed.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.health.remed.domain.models.DoseWithMedicineDomain
import com.app.health.remed.ui.screens.home.components.EmptyHomeScreen
import com.app.health.remed.ui.screens.home.components.IntakeProgress
import com.app.health.remed.ui.screens.home.components.ReminderListItem
import com.app.health.remed.ui.topbar.AppTopBar
import com.app.health.remed.utils.getCurrentDay

@Composable
fun HomeScreen(
    list: List<DoseWithMedicineDomain>,
    onDetail: (Int) -> Unit,
    onAddMed: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar()
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = { onAddMed.invoke() },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    modifier = Modifier.padding(16.dp),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Medicine"
                )
            }
        },
        modifier = Modifier.padding(16.dp)
    ) { paddingValues ->
        if (list.isEmpty()) {
            EmptyHomeScreen {
                onAddMed.invoke()
            }
        } else {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Your Medicines",
                style = MaterialTheme.typography.headlineSmall,
            )

            LazyColumn(
                modifier = Modifier.padding(paddingValues)
            ) {

                item {
                    IntakeProgress(
                        total = list.size,
                        taken = 0,
                        day = getCurrentDay(),
                    )
                }
                item {
                    Text(
                        modifier = Modifier.padding(16.dp, 8.dp),
                        text = "Doses",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.ExtraBold)
                    )
                }
                items(
                    items = list,
                ) { doseWithMedicine ->
                    doseWithMedicine.dosesList.forEach { dose ->
                        ReminderListItem(
                            medicine = doseWithMedicine.medicine,
                            dose = dose
                        )
                    }
                }
            }
        }
    }
}