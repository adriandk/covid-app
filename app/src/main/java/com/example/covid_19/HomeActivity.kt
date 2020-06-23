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

//        startAlarmBroadcastReceiver(this)

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

//    private fun startAlarmBroadcastReceiver(context: Context) {
//        val intent = Intent(context, DailyNotification::class.java)
//        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
//        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//        alarmManager.cancel(pendingIntent)
//        val calendar: Calendar = Calendar.getInstance()
//        calendar.timeInMillis = System.currentTimeMillis()
//        calendar.set(Calendar.HOUR_OF_DAY, 23)
//        calendar.set(Calendar.MINUTE, 15)
//        calendar.set(Calendar.SECOND, 0)
//        alarmManager[AlarmManager.RTC_WAKEUP, calendar.timeInMillis] = pendingIntent
//    }

}