package com.app.health.remed.ui.screens.add_medicine.components.grids

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineEvent
import com.app.health.remed.ui.screens.add_medicine.components.AddMedicineState
import com.app.health.remed.ui.screens.add_medicine.components.time.TimeSlotCard

@Composable
fun TimeSlots(
    state: AddMedicineState,
    onEvent: (AddMedicineEvent) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        itemsIndexed(state.timeSlots) { index, slot ->
            TimeSlotCard(
                time = slot,
                selected = state.activeSlotIndex == index,
                onClick = { onEvent(AddMedicineEvent.OnTimeSlotClicked(index)) }
            )
        }
    }
}