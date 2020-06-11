package com.example.covid_19.contact

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.covid_19.PhotoViewer
import com.example.covid_19.R
import kotlinx.android.synthetic.main.fragment_contact.*

class ContactFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gambar.setOnClickListener {
            val pass = Intent(context, PhotoViewer::class.java)
            pass.putExtra("Gambar", "BRUH")
            startActivity(pass)
        }
    }
}