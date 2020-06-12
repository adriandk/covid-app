package com.example.covid_19.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covid_19.R
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    lateinit var mainViewModel : GetAPI
    lateinit var adapter : Adapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = Adapter()
        adapter.notifyDataSetChanged()

        data_corona.layoutManager = LinearLayoutManager(context)
        data_corona.adapter = adapter

        mainViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(GetAPI::class.java)

        mainViewModel.setData()
        showload(true)
        mainViewModel.getData().observe(viewLifecycleOwner, Observer { DataItems ->
            if (DataItems != null){
                adapter.setData(DataItems)
                showload(false)
                dateTime()
            }
        })

        refresh_swipe.setOnRefreshListener {
            mainViewModel.setData()
            mainViewModel.getData().observe(viewLifecycleOwner, Observer { DataItems ->
                if (DataItems != null) {
                    adapter.setData(DataItems)
                    refresh_swipe.isRefreshing = false
                    dateTime()
                }
            })
        }

        if (savedInstanceState != null) {

        }
    }

    private fun dateTime() {
        val currentDate: String =
            SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault()).format(Date())
        tanggal.text = currentDate
    }

    private fun showload(state: Boolean) {
        if (state) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//
//    }

}