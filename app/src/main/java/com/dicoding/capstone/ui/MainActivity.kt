package com.dicoding.capstone.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.dicoding.capstone.R
import com.dicoding.capstone.databinding.ActivityMainBinding
import com.dicoding.capstone.ui.auth.LoginActivity
import com.dicoding.capstone.ui.auth.RegisterActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toSignInPage()
        toSignUpPage()
    }

    private fun toSignInPage() {
        val toSignIn = findViewById<AppCompatButton>(R.id.btnSignIn)
        toSignIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun toSignUpPage() {
        val toSignIn = findViewById<AppCompatButton>(R.id.btnSignUp)
        toSignIn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}