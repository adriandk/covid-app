package com.example.covid_19.international

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.R
import kotlinx.android.synthetic.main.activity_international_data.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

class InternationalData : AppCompatActivity() {

    lateinit var mainViewModel: GetAPIInternational
    lateinit var adapter: AdapterIntenational

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_international_data)

        adapter = AdapterIntenational()
        adapter.notifyDataSetChanged()

        rv_inter.layoutManager = LinearLayoutManager(this)
        rv_inter.adapter = adapter

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(GetAPIInternational::class.java)

        mainViewModel.setData()
        loading(true)
        mainViewModel.getData().observe(this, androidx.lifecycle.Observer { DataItems ->
            if (DataItems != null) {
                loading(false)
                adapter.setData(DataItems)
                tanggal()
            } else {
                Toast.makeText(this, "Please refresh", Toast.LENGTH_LONG).show()
            }
        })

        refresh_inter.setOnRefreshListener {
            mainViewModel.setData()
            loading(true)
            mainViewModel.getData().observe(this, androidx.lifecycle.Observer { DataItems ->
                if (DataItems != null) {
                    loading(false)
                    adapter.setData(DataItems)
                    tanggal()
                    refresh_inter.isRefreshing = false
                } else {
                    Toast.makeText(this, "Please refresh", Toast.LENGTH_LONG).show()
                }
            })
        }

        back_international.setOnClickListener {
            onBackPressed()
        }

    }

    fun loading(condition: Boolean) {
        if (condition) {
            loading_inter.visibility = View.VISIBLE
        } else {
            loading_inter.visibility = View.GONE
        }
    }

    private fun tanggal() {
        val tanggal = SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault()).format(Date())
        date_time.text = tanggal
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}