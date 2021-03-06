package com.example.covid_19

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.covid_19.Home.HomeFragment
import com.example.covid_19.Search.SearchFragment
import com.example.covid_19.contact.ContactFragment
import kotlinx.android.synthetic.main.activity_home.*
import java.util.*

class HomeActivity : AppCompatActivity() {

    val homeFrag = HomeFragment()
    val contactFrag = ContactFragment()
    val fragmentManager = supportFragmentManager
    var fragment: Fragment = homeFrag

    var namaFragment = "home"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        fragmentManager.beginTransaction().add(R.id.frame_layout, contactFrag, "2")
            .hide(contactFrag).commit()
        fragmentManager.beginTransaction().add(R.id.frame_layout, homeFrag, "1").commit()

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    namaFragment = "home"
                    fragmentManager.beginTransaction().hide(fragment).show(homeFrag).commit()
                    fragment = homeFrag
                }
                R.id.kontak -> {
                    namaFragment = "contact"
                    fragmentManager.beginTransaction().hide(fragment).show(contactFrag).commit()
                    fragment = contactFrag
                }
            }
            true
        }
    }

}