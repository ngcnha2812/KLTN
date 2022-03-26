package com.example.kltn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LearnCode : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learn_code)
        var mftName = findViewById<EditText>(R.id.mftName)
        var codeName = findViewById<EditText>(R.id.codeName)
        var code = findViewById<EditText>(R.id.code)

        findViewById<Button>(R.id.codeSubmit).setOnClickListener {
            addCode(mftName.text.toString(),codeName.text.toString(),code.text.toString())
            mftName.text = null
            codeName = null
            code = null
        }
    }

    private  fun addCode(mftName:String,codeName:String,code:String){
        database = Firebase.database(Constants.databaseURL).reference
        database.child("Fan").child(mftName).child(codeName).setValue(code).addOnSuccessListener {
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this,"Failed! Try again",Toast.LENGTH_SHORT).show()
        }
    }
}