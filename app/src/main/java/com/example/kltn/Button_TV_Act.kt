package com.example.kltn

import android.app.Dialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.json.JSONArray

class Button_TV_Act : AppCompatActivity() {

    private var database = Firebase.database(Constants.databaseURL).reference
    private var client = MqttConnect

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_tv)
        val path = intent.extras?.get("PATH").toString()
        client.Connect("TV")


        val backtv: Button = findViewById(R.id.backtv)
        val menu: Button = findViewById(R.id.menu)
        val power: Button = findViewById(R.id.power)
        val source: Button = findViewById(R.id.source)
        val up: Button = findViewById(R.id.Up)
        val left: Button = findViewById(R.id.left)
        val ok: Button = findViewById(R.id.ok)
        val right: Button = findViewById(R.id.right)
        val down: Button = findViewById(R.id.down)
        val back: Button = findViewById(R.id.back)
        val exit: Button = findViewById(R.id.exit)
        val volup: Button = findViewById(R.id.volup)
        val voldown: Button = findViewById(R.id.voldown)
        val setting: Button = findViewById(R.id.setting)
        val chup: Button = findViewById(R.id.chup)
        val chdown: Button = findViewById(R.id.chdown)
        val num7: Button = findViewById(R.id.num7)
        val num8: Button = findViewById(R.id.num8)
        val num9: Button = findViewById(R.id.num9)
        val num4: Button = findViewById(R.id.num4)
        val num5: Button = findViewById(R.id.num5)
        val num6: Button = findViewById(R.id.num6)
        val num1: Button = findViewById(R.id.num1)
        val num2: Button = findViewById(R.id.num2)
        val num3: Button = findViewById(R.id.num3)
        val ttv: Button = findViewById(R.id.ttv)
        val num0: Button = findViewById(R.id.num0)
        val mute: Button = findViewById(R.id.mute)

        power.setOnClickListener {
            sendCode("$path/POWER","TV")
        }
        source.setOnClickListener {
            sendCode("$path/SOURCE","TV")
        }
        chdown.setOnClickListener {
            sendCode("$path/CHDOWN","TV")
        }
        chup.setOnClickListener {
            sendCode("$path/CHUP","TV")
        }
        ok.setOnClickListener {
            sendCode("$path/OK","TV")
        }
        menu.setOnClickListener {
            sendCode("$path/MENU","TV")
        }
        ttv.setOnClickListener {
            sendCode("$path/TTV","TV")
        }
        voldown.setOnClickListener {
            sendCode("$path/VOLDOWN","TV")
        }
        volup.setOnClickListener {
            sendCode("$path/VOLUP","TV")
        }
    }

    override fun onStop() {
        super.onStop()
        client.Disconnect()
    }

    override fun onStart() {
        super.onStart()
        if(Progress.context != null )Progress.dismiss()
    }

    private fun sendCode(databasePath:String,topic:String)
    {
        database.child(databasePath).get().addOnSuccessListener {
            var jsonArray = JSONArray();
            if(it.value != null)
               jsonArray  = JSONArray(it.value.toString())
            var byteArray = ByteArray(jsonArray.length())
            for(i:Int in 0 until jsonArray.length()){
                byteArray[i] = jsonArray[i].toString().toByte()
            }
            if(byteArray.isNotEmpty()) client.Send(topic,byteArray)
        }
    }
}