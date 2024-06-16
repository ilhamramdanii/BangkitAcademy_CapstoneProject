package com.dicoding.capstone.local.theme

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.prefDataStore by preferencesDataStore("settings")

class SettingPreference(private val context: Context) {

    private val settingsDataStore = context.prefDataStore
    private val themeKEY = booleanPreferencesKey("theme_setting")

    private var data: Boolean = false

    fun getThemeSetting(): Flow<Boolean> =
        settingsDataStore.data.map { preferences ->
            preferences[themeKEY] ?: data
        }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        settingsDataStore.edit { preferences ->
            preferences[themeKEY] = isDarkModeActive
            data = isDarkModeActive
        }
    }
}