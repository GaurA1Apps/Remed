package com.app.health.remed.ui.screens.home

import com.app.health.remed.utils.MedicineType
import org.jetbrains.compose.resources.DrawableResource
import remed.composeapp.generated.resources.Res
import remed.composeapp.generated.resources.ic_drops
import remed.composeapp.generated.resources.ic_injection
import remed.composeapp.generated.resources.ic_med
import remed.composeapp.generated.resources.ic_pill
import remed.composeapp.generated.resources.ic_pill_home
import remed.composeapp.generated.resources.ic_syrup
import remed.composeapp.generated.resources.pills

fun MedicineType.getIcon(): DrawableResource {
    return when (this) {
        MedicineType.PILL -> Res.drawable.pills
        MedicineType.SYRUP -> Res.drawable.ic_syrup
        MedicineType.INJECTION -> Res.drawable.ic_injection
        MedicineType.DROPS -> Res.drawable.ic_drops
        MedicineType.OTHER -> Res.drawable.ic_med
    }
}
