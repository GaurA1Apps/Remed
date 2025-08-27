package com.app.health.remed.prefs

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DatastoreRepository(
    private val dataStore: DataStore<Preferences>
) {
    // Save Boolean
    suspend fun saveOnBoarding(value: Boolean): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                val prefKey = booleanPreferencesKey(IS_ONBOARDING_DONE)
                dataStore.edit { prefs ->
                    prefs.set(key = prefKey, value = value)
                }
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
    }

    // Get Boolean
    fun isOnBoardingDone(): Flow<Boolean> {
        val prefKey = booleanPreferencesKey(IS_ONBOARDING_DONE)
        try {
            return dataStore.data.map { prefs ->
                prefs[prefKey] ?: false
            }.flowOn(Dispatchers.IO)
        } catch (e: Exception) {
            e.printStackTrace()
            return dataStore.data.map { false }
        }
    }

    companion object {
        const val IS_ONBOARDING_DONE = "is_onboarding_done"
    }
}

@Composable
fun rememberDatastoreRepository(
    dataStore: DataStore<Preferences>
): DatastoreRepository {
    return DatastoreRepository(dataStore = dataStore)
}