package com.dicoding.capstone.local.theme

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class ThemeManager(private val context: Context) {

    fun setTheme(isDarkMode: Boolean) {
        val nightMode = if (isDarkMode) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }
}