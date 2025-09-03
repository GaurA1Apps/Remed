package com.app.health.remed.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.app.health.remed.data.entity.MedicineEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {

    @Upsert
    suspend fun insertMedicine(medicine: MedicineEntity)

    @Query("SELECT * FROM medicines ORDER BY hour, minute")
    fun getAllMedicines(): Flow<List<MedicineEntity>>

    @Query("SELECT * FROM medicines WHERE id = :id")
    suspend fun getMedicineById(id: Int): MedicineEntity

    @Query("DELETE FROM medicines WHERE id = :id")
    suspend fun deleteMedicineById(id: Int)

    @Query("DELETE FROM medicines")
    suspend fun deleteAllMedicines()
}
