package com.example.kltn

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.Adapter.ListDeviceAdapter
import com.example.kltn.Model.TVActModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TVAct : AppCompatActivity() {
    private lateinit var adapter:ListDeviceAdapter
    private val database = Firebase.database(Constants.databaseURL).reference
    private lateinit var model : TVActModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tvact)
        val listdev: RecyclerView = findViewById(R.id.list_TV)
        model = TVActModel()
        val extras = "${intent.extras?.get("roomName").toString().uppercase()}/TV"
        Log.d("check", extras)
        adapter = ListDeviceAdapter(extras)
        val lm = LinearLayoutManager(this)
        database.child("TV").addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                print(error)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val listTV = ArrayList<String>()
                snapshot.children.forEach {
                    listTV.add(it.key!!)
                }
                model.postList(listTV)
            }
        })
        model.listTV.observe(this, Observer {
            adapter.submitList(it)
            listdev.adapter = adapter
            listdev.layoutManager = lm
        })
        findViewById<Button>(R.id.TV_learn).setOnClickListener {
            val intent = Intent(this,LearnCode::class.java)
            intent.putExtra("PATH",extras)
            startActivity(intent)
        }
    }
}