package com.example.covid_19

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

fun startAlarmBroadcastReceiver(context: Context) {
    val intent = Intent(context, DailyNotification::class.java)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(pendingIntent)
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = System.currentTimeMillis()
    calendar.set(Calendar.HOUR_OF_DAY, 22)
    calendar.set(Calendar.MINUTE, 10)
    calendar.set(Calendar.SECOND, 0)
    alarmManager[AlarmManager.RTC_WAKEUP, calendar.timeInMillis] = pendingIntent
}