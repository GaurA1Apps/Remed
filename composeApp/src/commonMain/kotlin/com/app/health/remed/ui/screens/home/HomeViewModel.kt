package com.app.health.remed.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.health.remed.data.MedicineRepository
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val repository: MedicineRepository
) : ViewModel() {

    val medicinesList = repository.getAllMedicines().stateIn(
        initialValue = emptyList(),
        started = WhileSubscribed(5000),
        scope = viewModelScope
    )
}