package com.example.kltn.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.Adapter.ListDeviceAdapter
import com.example.kltn.Constants
import com.example.kltn.LearnCode
import com.example.kltn.R
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ACAct : AppCompatActivity() {
    private lateinit var adapter:ListDeviceAdapter
    private val database = Firebase.database(Constants.databaseURL).reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acact)
        val listdev: RecyclerView = findViewById(R.id.list_AC)
        val extras = "${intent.extras?.get("roomName").toString().uppercase()}/AC"
        Log.d("check", extras)
        adapter = ListDeviceAdapter(extras)
        val lm = LinearLayoutManager(this)
        database.child("AC").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                print(error)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val listAC = ArrayList<String>()
                snapshot.children.forEach {
                    listAC.add(it.key!!)
                }
                adapter.submitList(listAC)
                listdev.adapter = adapter
                listdev.layoutManager = lm
            }
        })
        findViewById<Button>(R.id.AC_learn).setOnClickListener {
            val intent = Intent(this, LearnCode::class.java)
            intent.putExtra("PATH",extras)
            startActivity(intent)
        }
    }
}