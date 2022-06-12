package com.example.kltn.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.kltn.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray

class TestDevice : AppCompatActivity() {
    private val database = Firebase.database(Constants.databaseURL).reference
    private var client = MqttConnect
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_device)
        val path = intent.extras?.get("PATH").toString().uppercase()
        val deviceName = path.split("/")[1]
        val item = intent.extras?.get("VALUE").toString().uppercase()
        Log.d("check","$path/$item")
        client.Connect(deviceName)
        findViewById<ImageView>(R.id.test_power).setOnClickListener {
            database.child("$deviceName/$item/POWER").get().addOnSuccessListener {
                var jsonArray = JSONArray();
                if(it.value != null)
                    jsonArray  = JSONArray(it.value.toString())
                val byteArray = ByteArray(jsonArray.length())
                for(i:Int in 0 until jsonArray.length()){
                    byteArray[i] = jsonArray[i].toString().toByte()
                }
                if(byteArray.isNotEmpty()) client.Send(deviceName,byteArray)
            }
        }
        findViewById<TextView>(R.id.deviceYes).setOnClickListener {
            database.child("ROOM/$path").setValue(item.toString().uppercase()).addOnSuccessListener {
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
        client.Disconnect()
    }
}