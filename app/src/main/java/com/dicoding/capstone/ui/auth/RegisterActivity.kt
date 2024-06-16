package com.dicoding.capstone.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.capstone.databinding.ActivityRegisterBinding
import com.dicoding.capstone.local.data.UserPreference
import com.dicoding.capstone.util.ViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterActivity : AppCompatActivity() {

    // View Binding Declaration
    private lateinit var binding: ActivityRegisterBinding

    // ViewModel Declaration
    private val viewModel: AuthViewModel by viewModels {
        val userPreference = UserPreference(this)
        ViewModelFactory(FirebaseAuth.getInstance(), FirebaseFirestore.getInstance(), userPreference)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        moveToLoginPage()
        setButtonOnClick()
    }

    private fun moveToLoginPage() {
        binding.tvSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setButtonOnClick() {
        binding.btnSignUp.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPass.text.toString().trim()
            val rePassword = binding.etRepass.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || rePassword.isEmpty()) {
                showToast("All fields must be filled")
                return@setOnClickListener
            }

            if (password.length < 8) {
                showToast("Password must be at least 8 characters")
                return@setOnClickListener
            }

            if (password != rePassword) {
                showToast("Password does not match")
                return@setOnClickListener
            }

            viewModel.registerUser(name, email, password) { success, message ->
                if (success) {
                    showToast("Registration successful")
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    showToast("Registration failed: $message")
                }
            }
        }
    }

    private fun showToast(message: String) {
        GlobalScope.launch {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
