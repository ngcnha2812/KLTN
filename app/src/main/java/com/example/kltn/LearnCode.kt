package com.example.kltn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray
import org.json.JSONObject

class LearnCode : AppCompatActivity() {
    private val database = Firebase.database(Constants.databaseURL).reference
    private var client : MqttConnect = MqttConnect
    private  val model : LearnModel = LearnModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_code)

        client.Connect("Learn")
        client.Async(model)
        val deviceType = findViewById<EditText>(R.id.deviceTypeName)
        val mftName = findViewById<EditText>(R.id.mftName)
        val code = findViewById<EditText>(R.id.code)
        val btnName = findViewById<EditText>(R.id.btnName)

        findViewById<Button>(R.id.codeSubmit).setOnClickListener {
            database.child("${deviceType.text.toString().uppercase()}/${mftName.text.toString().uppercase()}/${btnName.text.toString().uppercase()}").setValue(model.recvBuf.value)
            btnName.setText("")
            code.setText("")
        }
        model.recvBuf.observe(this, Observer { receiveCode ->
            if(receiveCode != null)
                code.setText("Code received")
        })
    }

    override fun onStop() {
        super.onStop()
        client.Disconnect()
    }
}