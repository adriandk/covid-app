package com.example.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_photo_viewer.*

class PhotoViewer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_viewer)

        val getImage = intent.getStringExtra("Gambar")
        when (getImage) {
            "BRUH" -> {
                photo_viewer.setImageResource(R.drawable.photo)
            }
        }

        button_back.setOnClickListener {
            finish()
        }
    }
}