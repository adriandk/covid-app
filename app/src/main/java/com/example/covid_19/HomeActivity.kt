package com.example.covid_19

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.covid_19.Home.HomeFragment
import com.example.covid_19.contact.ContactFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    var namaFragment = "home"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        loadFragment(HomeFragment())

        bottom_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    namaFragment = "home"
                    loadFragment(HomeFragment())
                }
//                R.id.search -> loadFragment(SearchFragment())
                R.id.kontak -> {
                    namaFragment = "kontak"
                    loadFragment(ContactFragment())
                }
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }


//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        outState.putString("selectedFragment", namaFragment)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        namaFragment = savedInstanceState.getString("selectedFragment").toString()
//        Log.d("Where are you?", "wait i stuck at $namaFragment")
//
//        when(namaFragment){
//            "home" ->{
//                loadFragment(HomeFragment())
//                bottom_nav.selectedItemId = R.id.home
//            }
//            "kontak" ->{
//                loadFragment(ContactFragment())
//                bottom_nav.selectedItemId = R.id.kontak
//            }
//        }
//    }
}