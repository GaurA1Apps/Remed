package com.app.health.remed.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.health.remed.data.entity.MedicineEntity
import com.app.health.remed.ui.screens.home.components.EmptyHomeScreen
import com.app.health.remed.ui.topbar.AppTopBar

@Composable
fun HomeScreen(
    list: List<MedicineEntity>,
    onDetail: (Int) -> Unit,
    onAddMed: () -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar()
        },
        floatingActionButton = {
            FloatingActionButton(
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
                items(list) { medicine ->
                    MedicineItem(medicine = medicine, onDetail = onDetail)
                }
            }
        }
    }
}

@Composable
fun MedicineItem(medicine: MedicineEntity, onDetail: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {
                onDetail.invoke(medicine.id)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = medicine.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Dosage: ${medicine.dosage}", style = MaterialTheme.typography.bodySmall) // Assuming 'dosage' field
            }
            Text(text = "Amount: ${medicine.amount}", style = MaterialTheme.typography.bodyMedium) // Assuming 'amount' field
        }
    }
}
