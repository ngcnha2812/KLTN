package com.example.kltn.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.kltn.Constants
import com.example.kltn.Model.DetailModel
import com.example.kltn.MqttConnect
import com.example.kltn.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray

class Custom_Btn : AppCompatActivity() {
    private var model = DetailModel()
    private var client = MqttConnect
    private var database = Firebase.database(Constants.databaseURL).reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_btn)
        val path = intent.extras?.get("PATH").toString()
        Log.d("check",path)
        client.Connect("CUSTOM")
        client.Subscribe("DETAIL")
        client.Async(model)

        findViewById<Button>(R.id.CustomPower).setOnClickListener {
            sendCode("$path/POWER","CUSTOM");
        }
        findViewById<Button>(R.id.GetDetail).setOnClickListener {
            sendCode("$path/DETAIL","CUSTOM")
        }
        findViewById<TextView>(R.id.CustomName).text = path.split("/")[1]

        model.detail.observe(this) {
            findViewById<TextView>(R.id.DeviceDetail).text = it
            //findViewById<TextView>(R.id.DeviceDetail).setTextColor(0xff0000)
        }
    }

    private fun sendCode(databasePath:String,topic:String)
    {
        database.child(databasePath).get().addOnSuccessListener {
            var jsonArray = JSONArray();
            if(it.value != null)
                jsonArray  = JSONArray(it.value.toString())
            var byteArray = ByteArray(jsonArray.length())
            if(jsonArray[1].toString().toInt() > 127) byteArray[1] = ((jsonArray[1].toString().toInt())/10).toByte()
            else byteArray[1] = (jsonArray[1].toString().toByte())
            for(i:Int in 0 until jsonArray.length() ){
                if(i != 1) byteArray[i] = jsonArray[i].toString().toByte()
            }
            if(byteArray.isNotEmpty()) client.Send(topic,byteArray)
        }
    }

    override fun onStop() {
        super.onStop()
        client.Disconnect()
    }
}