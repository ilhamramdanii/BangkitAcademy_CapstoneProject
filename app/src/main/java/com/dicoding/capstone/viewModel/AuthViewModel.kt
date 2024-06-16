package com.dicoding.capstone.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.capstone.local.data.UserPreference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AuthViewModel(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
    private val userPreference: UserPreference
) : ViewModel() {

    fun registerUser(name: String, email: String, password: String, token: String? = null, callback: (Boolean, String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // Register user with Firebase Auth
                auth.createUserWithEmailAndPassword(email, password).await()

                // Hash Password using bcrypt
                val hashedPassword = at.favre.lib.crypto.bcrypt.BCrypt.withDefaults().hashToString(12, password.toCharArray())

                // Create UserEntity
                val userMap = hashMapOf(
                    "name" to name,
                    "email" to email,
                    "token" to token,
                    "password" to hashedPassword
                )

                // Save to Firestore with the user's email as the document ID
                db.collection("users").document(email).set(userMap).await()

                // Save user data to SharedPreferences
                userPreference.saveUser(name, email, token ?: "")

                callback(true, null)
            } catch (e: Exception) {
                callback(false, e.message)
            }
        }
    }

    fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                auth.signInWithEmailAndPassword(email, password).await()

                // Retrieve user data from Firestore
                val userSnapshot = db.collection("users").document(email).get().await()
                if (!userSnapshot.exists()) {
                    callback(false, "Email tidak ditemukan")
                    return@launch
                }

                val storedHashPassword = userSnapshot.getString("password") ?: run {
                    callback(false, "Password tidak ditemukan")
                    return@launch
                }

                if (at.favre.lib.crypto.bcrypt.BCrypt.verifyer().verify(password.toCharArray(), storedHashPassword).verified) {
                    val token = java.util.UUID.randomUUID().toString()
                    db.collection("users").document(email).update("token", token).await()

                    val name = userSnapshot.getString("name") ?: ""
                    // Save user data to SharedPreferences
                    userPreference.saveUser(name, email, token)

                    callback(true, null)
                } else {
                    callback(false, "Password salah")
                }
            } catch (e: Exception) {
                callback(false, e.message)
            }
        }
    }

    fun getUser(): Map<String, String?> {
        return userPreference.getUser()
    }

    fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            auth.signOut()
            userPreference.clearUser()
        }
    }
}
