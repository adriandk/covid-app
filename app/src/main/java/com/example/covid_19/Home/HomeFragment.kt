package com.example.covid_19.Home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.DiagnosaActivity
import com.example.covid_19.EduActivity
import com.example.covid_19.R
import com.example.covid_19.international.InternationalData
import kotlinx.android.synthetic.main.fragment_home.*
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    lateinit var mainViewModel: GetAPI
    lateinit var adapter: Adapter

    private val BUNDLE_RECYCLER_LAYOUT = "HomeFragment.data_corona.items"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        if (savedInstanceState != null){
//            val savedRecyclerLayout = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT)
//            data_corona.layoutManager!!.onRestoreInstanceState(savedRecyclerLayout)
//        }

        adapter = Adapter()
        adapter.notifyDataSetChanged()
        adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT

        data_corona.layoutManager = LinearLayoutManager(context)
        data_corona.adapter = adapter

        mainViewModel =
            ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(GetAPI::class.java)

        mainViewModel.setData()
        showload(true)
        mainViewModel.getData().observe(viewLifecycleOwner, Observer { DataItems ->
            if (DataItems != null) {
                adapter.setData(DataItems)
                showload(false)
                dateTime()
                text_error.visibility = View.INVISIBLE
            } else {
                showload(false)
                text_error.visibility = View.VISIBLE
                Toast.makeText(context, "Please refresh", Toast.LENGTH_LONG).show()
            }
        })

        refresh_swipe.setOnRefreshListener {
            mainViewModel.setData()
            mainViewModel.getData().observe(viewLifecycleOwner, Observer { DataItems ->
                if (DataItems != null) {
                    adapter.setData(DataItems)
                    refresh_swipe.isRefreshing = false
                    dateTime()
                    text_error.visibility = View.INVISIBLE
                } else {
                    text_error.visibility = View.VISIBLE
                    Toast.makeText(context, "Please refresh", Toast.LENGTH_LONG).show()
                }
            })
        }

        cardview_fight.setOnClickListener {
            val pindah = Intent(Intent.ACTION_VIEW)
            pindah.data =
                Uri.parse("https://news.detik.com/berita/d-4942353/daftar-rumah-sakit-rujukan-covid-19-seluruh-indonesia/1")
            startActivity(pindah)
        }

        edukasi_covid.setOnClickListener {
            intent("edukasi")
        }

        diagnosa_covid.setOnClickListener {
            intent("diagnosa")

        }

        data_internasional.setOnClickListener {
            intent("data international")
        }

        data_icon.setOnClickListener {
            intent("data international")
        }

        diagnosa_icon.setOnClickListener {
            intent("diagnosa")
        }

        edukasi_icon.setOnClickListener {
            intent("edukasi")
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

    private fun intent(tujuan: String) {

        when (tujuan) {
            "edukasi" -> {
                val pindah = Intent(context, EduActivity::class.java)
                activity!!.overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                startActivity(pindah)
            }
            "data international" -> {
                val pindah = Intent(context, InternationalData::class.java)
                activity!!.overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                startActivity(pindah)
            }
            else -> {
                val pindah = Intent(context, DiagnosaActivity::class.java)
                activity!!.overridePendingTransition(R.anim.fadein, R.anim.fadeout)
                startActivity(pindah)
            }
        }
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, data_corona.layoutManager!!.onSaveInstanceState())
//    }

}