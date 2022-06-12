package com.example.kltn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val fan:ImageView = findViewById(R.id.fan)
        val tv:ImageView = findViewById(R.id.tv)
        val air:ImageView = findViewById(R.id.air)

        val roomName = intent.extras?.get("roomName").toString().uppercase()
        Log.d("check",roomName)
        val MeFan = Intent(this,FanAct::class.java)
        fan.setOnClickListener{
            MeFan.putExtra("roomName",roomName)
            startActivity(MeFan)
        }
        val MeTV = Intent(this,TVAct::class.java)
        tv.setOnClickListener{
            MeTV.putExtra("roomName",roomName)
            startActivity(MeTV)
        }
    }

}