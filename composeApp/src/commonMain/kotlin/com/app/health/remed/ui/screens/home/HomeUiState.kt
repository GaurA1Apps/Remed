package com.app.health.remed.ui.screens.home

import com.app.health.remed.domain.models.DoseWithMedicineDomain

data class HomeUiState(
    val doseWithMedicines: List<DoseWithMedicineDomain> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
