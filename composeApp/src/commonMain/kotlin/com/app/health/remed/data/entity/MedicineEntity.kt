package com.app.health.remed.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.health.remed.utils.DoseStatus
import com.app.health.remed.utils.Frequency
import com.app.health.remed.utils.MedicineType
import kotlinx.datetime.LocalTime

@Entity(tableName = "medicines")
data class MedicineEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val type: MedicineType,
    val dosage: String,
    val amount: Int,
    val frequency: String,        // Frequency.label
    val durationInDays: Int,
    val times: List<Long>
)