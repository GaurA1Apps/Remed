package com.app.health.remed.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<MedicineDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath("medicines.db")
    return Room.databaseBuilder<MedicineDatabase>(
        context = appContext,
        name = dbFile.absolutePath
    )
}
