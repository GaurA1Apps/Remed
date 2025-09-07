package com.app.health.remed.ui.screens.home

import com.app.health.remed.domain.Medicine

data class HomeUiState(
    val medicines: List<Medicine> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
