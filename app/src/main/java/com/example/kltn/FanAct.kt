package com.example.kltn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class FanAct : AppCompatActivity() {
    private lateinit var client  : MqttConnect
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fan)
    }
}