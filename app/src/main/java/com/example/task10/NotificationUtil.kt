package com.example.task10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

object NotificationUtil {
    const val NOTIFICATION_ID = 1
    const val CHANNEL_ID = "Task10"
    const val CHANNEL_NAME = "Airplane mode changes"
    const val DESCRIPTION = "Get notified every time airplane mode changes"

    fun showAirplaneModeNotification(context: Context, state: Boolean) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat
            .Builder(context, CHANNEL_ID)
            .setSmallIcon(if (state) R.drawable.ic_airplanemode_active_24 else R.drawable.ic_airplanemode_inactive_24)
            .setContentTitle("Airplane Mode")
            .setContentText(("Airplane mode was " + if (state) "enabled" else "disabled") + " - ✈")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        manager.notify(NOTIFICATION_ID, notification.build())
    }

    fun createNotificationChannel(context: Context) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, importance)
        channel.description = DESCRIPTION

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)
    }
}