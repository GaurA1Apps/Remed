package com.app.health.remed.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.health.remed.data.MedicineRepository
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val repository: MedicineRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = repository.getTodayDoses()
        .map { doseWithMedicine ->
            HomeUiState(
                doseWithMedicines = doseWithMedicine,
                isLoading = false,
                errorMessage = null
            )
        }
        .onStart { emit(HomeUiState(isLoading = true)) }
        .catch { e ->
            emit(HomeUiState(errorMessage = e.message ?: "Unknown error"))
        }
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribed(5000),
            initialValue = HomeUiState(isLoading = true)
        )
}