package com.app.health.remed.data

import DoseDao
import com.app.health.remed.data.dao.MedicineDao
import com.app.health.remed.data.entity.DoseEntity
import com.app.health.remed.data.entity.MedicineEntity
import com.app.health.remed.data.mappers.toDomain
import com.app.health.remed.domain.models.DoseWithMedicineDomain
import com.app.health.remed.utils.DoseStatus
import com.app.health.remed.utils.dateToEpochMillis
import com.app.health.remed.utils.now
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMap
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate

class MedicineRepository(
    private val medicineDao: MedicineDao,
    private val doseDao: DoseDao,
) {
    suspend fun getMedicineById(id: Int): MedicineEntity? = medicineDao.getMedicineById(id)

    //On Saving a Medicine
    suspend fun addMedicine(medicine: MedicineEntity) {
        return withContext(Dispatchers.IO) {
            val medicineId = medicineDao.insertMedicine(medicine)
            val today = LocalDate.now()

            val doses = medicine.times.map { time ->
                DoseEntity(
                    medicineId = medicineId.toInt(),
                    date = today.dateToEpochMillis(),         // ext fun
                    time = time,     // ext fun
                    status = DoseStatus.SCHEDULED
                )
            }

            doseDao.upsertDoses(doses)
        }
    }

    //On App Launch
    suspend fun ensureTodayDoses() {
        return withContext(Dispatchers.IO) {
            val todayMillis = LocalDate.now().dateToEpochMillis()
            if (doseDao.getAnyDoseForDate(todayMillis) != null) return@withContext

            val medicines = medicineDao.getAllMedicinesOnce()
            val today = LocalDate.now()

            val doses = medicines.flatMap { medicine ->
                medicine.times.map { time ->
                    DoseEntity(
                        medicineId = medicine.id,
                        date = today.dateToEpochMillis(),
                        time = time,
                        status = DoseStatus.SCHEDULED
                    )
                }
            }

            doseDao.upsertDoses(doses)
        }
    }

    //On Home Screen Load
    fun getTodayDoses(): Flow<List<DoseWithMedicineDomain>> {
        val todayDateMillis = LocalDate.now().dateToEpochMillis()
        return doseDao.getDosesWithMedicineForToday().map { list ->
            list.flatMap { it.toDomain() }// using your mapper
        }
    }

    suspend fun deleteMedicineById(medicineId: Int) = medicineDao.deleteMedicineById(medicineId)
}

