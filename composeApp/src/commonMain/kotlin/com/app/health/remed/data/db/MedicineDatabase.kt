package com.app.health.remed.data.db

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.app.health.remed.data.dao.MedicineDao
import com.app.health.remed.data.entity.MedicineEntity

@Database(entities = [MedicineEntity::class], version = 1, exportSchema = false)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class MedicineDatabase: RoomDatabase() {

    abstract fun medicineDao(): MedicineDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<MedicineDatabase> {
    override fun initialize(): MedicineDatabase
}
