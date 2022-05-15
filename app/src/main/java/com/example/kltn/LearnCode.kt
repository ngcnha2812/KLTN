package com.example.kltn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LearnCode : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private var client : MqttConnect = MqttConnect
    private  val model : LearnModel = LearnModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_code)
        val extras = intent.extras?.get("PATH")
        client.Connect("Learn")
        client.Async(model)
        var type = findViewById<EditText>(R.id.typeName)
        var mftName = findViewById<EditText>(R.id.mftName)
        var codeName = findViewById<EditText>(R.id.codeName)
        var code = findViewById<EditText>(R.id.code)

        findViewById<Button>(R.id.codeSubmit).setOnClickListener {
            addCode(type.text.toString(),mftName.text.toString(),codeName.text.toString(),code.text.toString())
            //mftName.setText("")
            //codeName.setText("")
            //code.setText("")
        }
        model.code.observe(this, Observer { receiveCode ->
            code.setText(receiveCode)
        })
    }

    private  fun addCode(type:String,mftName:String,codeName:String,code:String){
        database = Firebase.database(Constants.databaseURL).reference
        database.child("$type/$mftName/$codeName").setValue(code).addOnSuccessListener {
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Failed! Try again",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStop() {
        super.onStop()
        client.Disconnect()
    }
}