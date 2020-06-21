package com.example.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EduActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edu)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}