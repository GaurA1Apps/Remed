package com.app.health.remed.navigation

sealed class NavigationEvent {
    data class  GoToDetails(val id: Int) : NavigationEvent()
    data object GoBack : NavigationEvent()
}