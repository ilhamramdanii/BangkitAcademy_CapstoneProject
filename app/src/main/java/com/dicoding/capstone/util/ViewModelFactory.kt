package com.dicoding.capstone.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.capstone.local.data.UserPreference
import com.dicoding.capstone.ui.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ViewModelFactory(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val userPreference: UserPreference
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(auth, db, userPreference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
