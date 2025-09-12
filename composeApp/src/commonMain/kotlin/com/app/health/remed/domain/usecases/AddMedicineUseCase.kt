package com.app.health.remed.domain.usecases

import com.app.health.remed.core.ReminderManager
import com.app.health.remed.data.MedicineRepository
import com.app.health.remed.domain.mappers.toEntity
import com.app.health.remed.domain.models.Medicine

class AddMedicineUseCase(
    private val repository: MedicineRepository,
    private val reminderManager: ReminderManager
) {
    suspend operator fun invoke(medicine: Medicine) {
        // 1. Save in DB (medicine + today's doses)
        val doseTimings = medicine.times
        val medicineEntity = medicine.toEntity()
        repository.addMedicine(medicineEntity)

        //2. Schedule reminders (via Alarmee)

        doseTimings.forEach { time ->
            reminderManager.scheduleDailyDose(
                medicine.name,
                medicine.amount,
                medicine.type.toString(),
                time
            )
        }
    }
}