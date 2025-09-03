package com.app.health.remed.data

import com.app.health.remed.data.dao.MedicineDao
import com.app.health.remed.data.entity.MedicineEntity
import kotlinx.coroutines.flow.Flow

class MedicineRepository(
    private val dao: MedicineDao
) {
    fun getAllMedicines(): Flow<List<MedicineEntity>> = dao.getAllMedicines()

    suspend fun getMedicineById(id: Int): MedicineEntity? = dao.getMedicineById(id)

    suspend fun insertMedicine(medicine: MedicineEntity) = dao.insertMedicine(medicine)

    suspend fun deleteMedicineById(medicineId: Int) = dao.deleteMedicineById(medicineId)
}

