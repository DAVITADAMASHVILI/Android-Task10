package com.example.task10

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AirplaneModeChangeReceiver: BroadcastReceiver() {
    var modeChangeListener: AirplaneModeChangeListener? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        val state: Boolean = intent?.getBooleanExtra("state", false) ?: return
        modeChangeListener?.onChange(state)
    }
}

interface AirplaneModeChangeListener {
    fun onChange(state: Boolean)
}