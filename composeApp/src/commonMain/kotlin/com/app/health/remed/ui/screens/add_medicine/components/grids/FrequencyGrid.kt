package com.app.health.remed.ui.screens.add_medicine.components.grids

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineEvent
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineState
import com.app.health.remed.utils.Frequency

@Composable
fun FrequencyGrid (
    state: AddMedicineState,
    onEvent: (AddMedicineEvent) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.padding(bottom = 8.dp).heightIn(max = 350.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        userScrollEnabled = false
    ) {
        items(Frequency.all) { frequency ->
            SelectableCard(
                label = frequency.label,
                isFrequency = true,
                isDuration = false,
                selected = state.selectedFrequency == frequency,
                onClick = {
                    onEvent(AddMedicineEvent.OnFrequencySelected(frequency))
                }
            )
        }
    }
}