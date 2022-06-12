package com.example.kltn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.Adapter.ListDeviceAdapter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FanAct : AppCompatActivity() {
    private lateinit var adapter:ListDeviceAdapter
    private val database = Firebase.database(Constants.databaseURL).reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fan)
        val listdev: RecyclerView = findViewById(R.id.list_FAN)
        val extras = "${intent.extras?.get("roomName").toString().uppercase()}/FAN"
        Log.d("check", extras)
        adapter = ListDeviceAdapter(extras)
        val lm = LinearLayoutManager(this)
        database.child("FAN").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                print(error)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val listTV = ArrayList<String>()
                snapshot.children.forEach {
                    listTV.add(it.key!!)
                }
                adapter.submitList(listTV)
                listdev.adapter = adapter
                listdev.layoutManager = lm
            }
        })
        findViewById<Button>(R.id.FAN_learn).setOnClickListener {
            val intent = Intent(this,LearnCode::class.java)
            intent.putExtra("PATH",extras)
            startActivity(intent)
        }
    }
}