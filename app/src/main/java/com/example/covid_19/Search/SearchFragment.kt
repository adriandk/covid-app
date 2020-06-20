package com.example.covid_19.Search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    lateinit var mainViewModel: GetAPIProvinsi
    lateinit var adapter: AdapterProvinsi

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdapterProvinsi()
        adapter.notifyDataSetChanged()
        adapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        data_provinsi.layoutManager = LinearLayoutManager(context)
        data_provinsi.adapter = adapter

        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(GetAPIProvinsi::class.java)

        mainViewModel.setData()
        showLoad(true)
        mainViewModel.getData().observe(viewLifecycleOwner, Observer { Dataitems ->
            if (Dataitems != null) {
                showLoad(false)
                adapter.setData(Dataitems)
            } else {
                Toast.makeText(context, "Please refresh", Toast.LENGTH_LONG).show()
            }
        })


    }

    fun showLoad(state: Boolean) {
        if (state) {
            loadingprovinsi.visibility = View.VISIBLE
        } else {
            loadingprovinsi.visibility = View.INVISIBLE
        }
    }
}