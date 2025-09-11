package com.app.health.remed.data.db

import DoseDao
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.app.health.remed.data.dao.MedicineDao
import com.app.health.remed.data.entity.DoseEntity
import com.app.health.remed.data.entity.MedicineEntity

@Database(entities = [MedicineEntity::class, DoseEntity::class], version = 1, exportSchema = false)
@ConstructedBy(AppDatabaseConstructor::class)
@TypeConverters(Converters::class)
abstract class MedicineDatabase: RoomDatabase() {

    abstract fun medicineDao(): MedicineDao
    abstract fun doseDao(): DoseDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")

expect object AppDatabaseConstructor : RoomDatabaseConstructor<MedicineDatabase> {
    override fun initialize(): MedicineDatabase
}
