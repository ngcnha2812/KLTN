package com.example.kltn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import android.os.Looper
import kotlin.concurrent.timer

class Splash : AppCompatActivity() {
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.`splash`)
        val menu = Intent(this,Menu::class.java)
        handler = Handler(Looper.getMainLooper());
        val runnable: Runnable = object :Runnable {
            override fun run() {
                finish()
                startActivity(menu)
            }
        }
        handler.postDelayed(runnable,5000);

    }
}