
package com.example.kltn.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_ac)
        val path = intent.extras?.get("PATH").toString()
        client.Connect("AC")

        findViewById<TextView>(R.id.manft_ac_name).text = path.split("/")[1]
        findViewById<LinearLayout>(R.id.AC_PowerOn).setOnClickListener {
            sendCode("$path/POWERON","AC")
        }
        findViewById<LinearLayout>(R.id.AC_PowerOff).setOnClickListener {
            sendCode("$path/POWEROFF","AC")
        }
        findViewById<ImageView>(R.id.AC_FanUp).setOnClickListener {
            sendCode("$path/FANUP","AC")
        }
        findViewById<ImageView>(R.id.AC_FanDown).setOnClickListener {
            sendCode("$path/FANDOWN","AC")
        }
        findViewById<ImageView>(R.id.AC_TempUp).setOnClickListener {
            sendCode("$path/TEMPUP","AC")
        }
        findViewById<ImageView>(R.id.AC_TempDown).setOnClickListener {
            sendCode("$path/TEMPDOWN", "AC")
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