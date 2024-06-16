package com.dicoding.capstone.local.data

import android.content.Context
import android.content.SharedPreferences

class UserPreference(context: Context) {
    private val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val KEY_NAME = "name"
        private const val KEY_EMAIL = "email"
        private const val KEY_TOKEN = "token"
    }

    fun saveUser(name: String, email: String, token: String) {
        val editor = preferences.edit()
        editor.putString(KEY_NAME, name)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun getUser(): Map<String, String?> {
        return mapOf(
            KEY_NAME to preferences.getString(KEY_NAME, null),
            KEY_EMAIL to preferences.getString(KEY_EMAIL, null),
            KEY_TOKEN to preferences.getString(KEY_TOKEN, null)
        )
    }

    fun clearUser() {
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}
