package com.app.health.remed.ui.screens.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import dev.icerock.moko.permissions.notifications.RemoteNotificationPermission
import kotlinx.coroutines.launch

class PermissionViewModel(
    val permissionsController: PermissionsController
) : ViewModel() {

    var state by mutableStateOf<PermissionState>(PermissionState.NotDetermined)
        private set

    init {
        viewModelScope.launch {
            state = permissionsController.getPermissionState(RemoteNotificationPermission)
        }
    }
    fun requestNotificationPermission(
        onPermissionResult: (PermissionState) -> Unit
    ) {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(RemoteNotificationPermission)
                state = PermissionState.Granted
            } catch(deniedAlways: DeniedAlwaysException) {
                state = PermissionState.DeniedAlways
            } catch(denied: DeniedException) {
                state = PermissionState.Denied
            } catch (e: RequestCanceledException) {
                e.printStackTrace()
            } finally {
                onPermissionResult(state)
            }
        }
    }
}