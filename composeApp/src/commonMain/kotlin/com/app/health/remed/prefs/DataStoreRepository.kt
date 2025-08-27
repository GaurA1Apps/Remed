package com.app.health.remed.prefs

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatastoreRepository(
    private val dataStore: DataStore<Preferences>
) {
    // Save Boolean
    suspend fun saveOnBoarding(value: Boolean) : Boolean {
        try {
            val prefKey = booleanPreferencesKey(IS_ONBOARDING_DONE)
            dataStore.edit { prefs ->
                prefs.set(key = prefKey, value = value)
            }
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
    }

    // Get Boolean
    fun isOnBoardingDone(): Flow<Boolean> {
        val prefKey = booleanPreferencesKey(IS_ONBOARDING_DONE)
        try {
            return dataStore.data.map { prefs ->
                prefs[prefKey] ?: false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return dataStore.data.map { false }
        }
    }

    companion object {
        const val IS_ONBOARDING_DONE = "is_onboarding_done"
    }
}