package com.example.kltn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class TVAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvact)
        val tv_me: Button = findViewById(R.id.TV_Midea)
        val MeTV = Intent(this,Buttom_TV_Act::class.java)
        tv_me.setOnClickListener {
            startActivity(MeTV)
        }
    }
}