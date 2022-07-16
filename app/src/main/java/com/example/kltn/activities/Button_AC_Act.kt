
package com.example.kltn.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.kltn.Constants
import com.example.kltn.Model.DetailModel
import com.example.kltn.MqttConnect
import com.example.kltn.Progress
import com.example.kltn.R
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray

class Button_AC_Act : AppCompatActivity() {
    private var database = Firebase.database(Constants.databaseURL).reference
    private var client = MqttConnect
    private var model = DetailModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_ac)
        val path = intent.extras?.get("PATH").toString()
        client.Connect("AC")
        client.Subscribe("DETAIL")
        client.Async(model)

        findViewById<TextView>(R.id.manft_ac_name).text = path.split("/")[1]

        findViewById<Button>(R.id.GetACDetail).setOnClickListener {
            findViewById<TextView>(R.id.ACDetail).text = model.detail.value
            findViewById<TextView>(R.id.ACDetail).setTextColor(0xFF0000)
        }
    }

    override fun onStop() {
        super.onStop()
        client.Disconnect()
    }

    override fun onStart() {
        super.onStart()
        if(Progress.context != null ) Progress.dismiss()
    }

    private fun sendCode(databasePath:String,topic:String)
    {
        database.child(databasePath).get().addOnSuccessListener {
            var jsonArray = JSONArray();
            if(it.value != null)
                jsonArray  = JSONArray(it.value.toString())
            val byteArray = ByteArray(jsonArray.length())
            for(i:Int in 0 until jsonArray.length()){
                byteArray[i] = jsonArray[i].toString().toByte()
            }
            if(byteArray.isNotEmpty()) client.Send(topic,byteArray)
        }
    }
}