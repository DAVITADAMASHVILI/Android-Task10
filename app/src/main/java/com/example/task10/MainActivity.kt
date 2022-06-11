package com.example.task10

import android.content.Intent
import android.content.IntentFilter
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.example.task10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AirplaneModeChangeListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        NotificationUtil.createNotificationChannel(this)
        registerAirplaneModeReceiver()
        val airplaneModeStatus = Settings.System.getInt(contentResolver, Settings.Global.AIRPLANE_MODE_ON) == 1
        configureText(airplaneModeStatus)
    }

    override fun onChange(state: Boolean) {
        configureText(state)
        NotificationUtil.showAirplaneModeNotification(this, state)
    }

    private fun configureText(state: Boolean) {
        binding.textView.text = "Airplane mode is " + if (state) "enabled" else "disabled"
        binding.textView.setTextColor(if (state) Color.BLUE else Color.RED)
    }

    private fun registerAirplaneModeReceiver() {
        val airplaneReceiver = AirplaneModeChangeReceiver()
        airplaneReceiver.modeChangeListener = this
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(airplaneReceiver, it)
        }
    }
}