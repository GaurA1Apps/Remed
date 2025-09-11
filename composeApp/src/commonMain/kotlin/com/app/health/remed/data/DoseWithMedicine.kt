package com.app.health.remed.data

import androidx.room.Embedded
import androidx.room.Relation
import com.app.health.remed.data.entity.DoseEntity
import com.app.health.remed.data.entity.MedicineEntity

data class DoseWithMedicine(
    @Embedded val medicineEntity: MedicineEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "medicineId"
    )
    val doseEntity: List<DoseEntity>
)
