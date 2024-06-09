package com.dicoding.capstone.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import at.favre.lib.crypto.bcrypt.BCrypt
import com.dicoding.capstone.databinding.ActivityLoginBinding
import com.dicoding.capstone.ui.forgotPassword.ForgotPasswordActivity
import com.dicoding.capstone.ui.MainActivity
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class LoginActivity : AppCompatActivity() {

    // View Binding Declaration
    private lateinit var binding: ActivityLoginBinding

    // Firebase Firestore Declaration
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Database Initiation
        db = FirebaseFirestore.getInstance()

        moveToForgotPassword()
        moveToRegistrationPage()
        setButtonOnClick()
    }

    private fun moveToForgotPassword() {
        binding.tvForgot.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }

    private fun moveToRegistrationPage() {
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setButtonOnClick() {
        // Set onClickListener for Login button
        binding.btnSignIn.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPass.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Search User From Database
            db.collection("users")
                .whereEqualTo("email", email)
                .get()
                .addOnSuccessListener { documents ->
                    if (documents.isEmpty) {
                        Toast.makeText(this, "Email tidak ditemukan", Toast.LENGTH_SHORT).show()
                        return@addOnSuccessListener
                    }

                    for (document in documents) {
                        val storedHashPassword = document.getString("password")

                        // Password verification bcrypt
                        if (storedHashPassword != null && BCrypt.verifyer().verify(password.toCharArray(), storedHashPassword).verified) {
                            // Generate a new token
                            val token = UUID.randomUUID().toString()

                            // Update the user document with the new token
                            db.collection("users").document(document.id)
                                .update("token", token)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                                    // Redirect to Main Activity
                                    val intent = Intent(this, MainActivity::class.java)
                                    startActivity(intent)
                                    finish()
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(this, "Gagal menyimpan token: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                        } else {
                            Toast.makeText(this, "Password salah", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Login gagal: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}
