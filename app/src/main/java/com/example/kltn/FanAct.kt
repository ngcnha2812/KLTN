package com.example.kltn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import io.grpc.internal.JsonParser

class FanAct : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var client  : MqttConnect
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fan)
        database = Firebase.database(Constants.databaseURL).reference
        database.child("Fan").child("Samsung").get().addOnSuccessListener {
           Log.d("firebase","${it.value}")
        }.addOnFailureListener {
            Log.d("error","Error occured")
        }

    }
}