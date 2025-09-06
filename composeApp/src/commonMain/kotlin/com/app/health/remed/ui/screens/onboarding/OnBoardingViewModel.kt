package com.app.health.remed.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.health.remed.prefs.DatastoreRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {

    val onBoardingState = datastoreRepository.isOnBoardingDone().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        false
    )


    fun onGetStartedClick() {
        try {
            viewModelScope.launch {
                datastoreRepository.saveOnBoarding(value = true)
            }
        } catch (e: Exception) {

        }
    }
}