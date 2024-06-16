package com.dicoding.capstone.ui.detail

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.capstone.R
import com.dicoding.capstone.databinding.ActivityDetailBinding
import java.io.File

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnCamera.setOnClickListener { startCameraX() }
//        binding.btnGallery.setOnClickListener { startGallery() }
    }

//    private fun startGallery() {
//        val intent = Intent()
//        intent.action = Intent.ACTION_GET_CONTENT
//        intent.type = "image/*"
//        val chooser = Intent.createChooser(intent, "Choose a Picture")
//        launcherIntentGallery.launch(chooser)
//    }

//    private val launcherIntentGallery = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) { result ->
//        if (result.resultCode == RESULT_OK) {
//            val selectedImg = result.data?.data as Uri
//            selectedImg.let { uri ->
//                val getFile = uriToFile(uri, this@DetailActivity)
//                myFile = getFile
//                binding.iv.setImageURI(uri)
//            }
//        }
//    }

//    private fun startCameraX() {
//        val intent = Intent(this, CameraActivity::class.java)
//        launcherIntentCameraX.launch(intent)
//    }

//    private val launcherIntentCameraX = registerForActivityResult(
//        ActivityResultContracts.StartActivityForResult()
//    ) {
//        if (it.resultCode == CAMERA_X_RESULT) {
//            myFile = (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//                it.data?.getSerializableExtra("picture", File::class.java)
//            } else {
//                it.data?.getSerializableExtra("picture")
//            } as? File)!!
//
//            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean
//
//            myFile.let { file ->
//                if (file != null) {
//                    rotateFile(file, isBackCamera)
//                }
//                intent.putExtra(EXTRA_RESULT, myFile)
//                if (file != null) {
//                    binding.ivImgDesc.setImageBitmap(BitmapFactory.decodeFile(file.path))
//                }
//            }
//        }
//    }
}