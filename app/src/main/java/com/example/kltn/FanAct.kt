package com.example.kltn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.database.DatabaseReference

class FanAct : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var client  : MqttConnect
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fan)
        findViewById<Button>(R.id.F_Learn).setOnClickListener {
            startActivity(Intent(this,LearnCode::class.java))
        }
    }
}