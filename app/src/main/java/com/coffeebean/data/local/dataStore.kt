package com.coffeebean.data.local

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore("app_preferences")

class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val ONBOARDING_KEY = booleanPreferencesKey("onboarding_completed")
    }

    val onboardingCompleted: Flow<Boolean> = context.dataStore.data
        .map { prefs -> prefs[ONBOARDING_KEY] ?: false }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[ONBOARDING_KEY] = completed
        }
    }
}
