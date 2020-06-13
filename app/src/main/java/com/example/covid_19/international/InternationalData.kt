package com.example.covid_19.international

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covid_19.R
import kotlinx.android.synthetic.main.activity_international_data.*
import java.text.SimpleDateFormat
import java.util.*

class InternationalData : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_international_data)

        refresh_inter.setOnRefreshListener {
            tanggal()
            refresh_inter.isRefreshing = false
        }

    }

    private fun tanggal() {
        val tanggal = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault()).format(Date())
        date_time.text = tanggal
    }
}