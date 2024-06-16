package com.dicoding.capstone.ui.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.R
import com.dicoding.capstone.adapter.ResultAdapter
import com.dicoding.capstone.data.local.model.ResultModel
import com.dicoding.capstone.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val results = listOf(
            ResultModel("Nama 1", "Status 1", "https://example.com/photo1.jpg"),
            ResultModel("Nama 2", "Status 2", "https://example.com/photo2.jpg"),
            ResultModel("Nama 3", "Status 3", "https://example.com/photo3.jpg")
        )

        binding.rvResult.layoutManager = LinearLayoutManager(this)
        binding.rvResult.adapter = ResultAdapter(results)
    }
}