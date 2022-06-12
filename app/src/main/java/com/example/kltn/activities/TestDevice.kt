package com.example.kltn.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.kltn.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TestDevice : AppCompatActivity() {
    private val database = Firebase.database(Constants.databaseURL).reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_device)
        val path = intent.extras?.get("PATH")
        val item = intent.extras?.get("VALUE")
        findViewById<ImageView>(R.id.test_power).setOnClickListener {

        }
        findViewById<TextView>(R.id.deviceYes).setOnClickListener {
            if(path!=null) database.child("ROOM/$path").setValue(item.toString().uppercase()).addOnSuccessListener {
                val intent = Intent(this,Room::class.java)
                startActivity(intent)
                finish()
            }
        }
        findViewById<TextView>(R.id.deviceNo).setOnClickListener {
            finish()
        }
    }

    override fun onStop() {
        super.onStop()
        Progress.dismiss()
    }
}