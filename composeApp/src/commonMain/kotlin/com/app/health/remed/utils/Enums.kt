package com.app.health.remed.utils

import org.jetbrains.compose.resources.DrawableResource
import remed.composeapp.generated.resources.Res
import remed.composeapp.generated.resources.ic_drops
import remed.composeapp.generated.resources.ic_injection
import remed.composeapp.generated.resources.ic_med
import remed.composeapp.generated.resources.ic_syrup
import remed.composeapp.generated.resources.pills


enum class MedicineType(val label: String, val icon: DrawableResource) {
    PILL("Pill", Res.drawable.pills),
    SYRUP("Syrup", Res.drawable.ic_syrup),
    INJECTION("Injection", Res.drawable.ic_injection),
    DROPS("Drops", Res.drawable.ic_drops),
    OTHER("Other", Res.drawable.ic_med);

    companion object {
        fun fromString(type: String): MedicineType {
            return entries.find { it.label == type } ?: OTHER
        }

        val all = entries.toList()
    }
}

enum class DoseStatus { SCHEDULED, TAKEN, SKIPPED }


sealed class Frequency(val label: String, val slots: Int) {
    object Once : Frequency("Once daily", 1)
    object Twice : Frequency("Twice daily", 2)
    object Thrice : Frequency("Three times daily", 3)
    object FourTimes : Frequency("Four times daily", 4)

    companion object {
        val all = listOf(Once, Twice, Thrice, FourTimes)
    }
}


sealed class Duration(val label: String, val numberOfDays: Int) {
    object Three : Duration("3 days", 3)
    object Seven : Duration("7 days", 7)
    object Fourteen : Duration("14 days", 14)
    object Infinite : Duration("30 days", 30)

    companion object {
        val all = listOf(Three, Seven, Fourteen, Infinite)
    }
}


