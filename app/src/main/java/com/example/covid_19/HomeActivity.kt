package com.example.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.covid_19.Home.HomeFragment
import com.example.covid_19.contact.ContactFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

//    final val STATE_TITLE : String = "state_title"
//    final val STATE_LIST : String = "state_list"
//    final val STATE_MODE : String = "state_mode"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadFragment(HomeFragment())

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> loadFragment(HomeFragment())
//                R.id.search -> loadFragment(SearchFragment())
                R.id.kontak -> loadFragment(ContactFragment())
            }
            true
        }

        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
        } else {
            loadFragment(ContactFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//
//    }
}