package com.example.subtrack.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.subtrack.data.Subscription
import java.util.Calendar

object AlarmScheduler {

    fun scheduleSubscriptionAlarm(context: Context, subscription: Subscription) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, SubscriptionReceiver::class.java).apply {
            putExtra("PLATFORM_NAME", subscription.platformAdi)
            putExtra("PRICE", "${subscription.fiyat} ${subscription.paraBirimi}")
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            subscription.platformAdi.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            add(Calendar.MINUTE, 1)
        }

        try {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}