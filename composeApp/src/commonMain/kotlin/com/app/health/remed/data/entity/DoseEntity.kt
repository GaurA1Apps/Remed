package com.app.health.remed.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.app.health.remed.utils.DoseStatus

@Entity(
    tableName = "doses",
    foreignKeys = [
        ForeignKey(
            entity = MedicineEntity::class,
            parentColumns = ["id"],
            childColumns = ["medicineId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        androidx.room.Index(value = ["medicineId"])
    ]
)
data class DoseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val medicineId: Int,
    val date: Long,              // which day
    val time: Long,              // which time slot
    val status: DoseStatus = DoseStatus.SCHEDULED
)
