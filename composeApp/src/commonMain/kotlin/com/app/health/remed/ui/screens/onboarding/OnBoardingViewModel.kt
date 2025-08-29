package com.app.health.remed.ui.screens.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.health.remed.prefs.DatastoreRepository
import kotlinx.coroutines.launch

class OnBoardingViewModel(
    private val datastoreRepository: DatastoreRepository
) : ViewModel() {

    fun onGetStartedClick() {
        try {
            viewModelScope.launch {
                datastoreRepository.saveOnBoarding(value = true)
            }
        } catch (e: Exception) {

        }
    }
}