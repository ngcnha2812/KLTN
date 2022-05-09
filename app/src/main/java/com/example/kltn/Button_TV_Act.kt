package com.example.kltn

import android.app.Dialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Button_TV_Act : AppCompatActivity() {

    private var dataBase = Firebase.database(Constants.databaseURL).reference
    private var mqttClient = MqttConnect
    private var _power:String =  "PowerOff"
    private var bitnum:Long = 0;
    private var protocol:String = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_tv)
        val name = intent.extras?.get("name")
        Log.d("check","$name")
        mqttClient.Connect("TV")
        dataBase.child("TV/Toshiba/bitnum").get().addOnSuccessListener {
            bitnum = it.value as Long
        }
        dataBase.child("TV/Toshiba/protocol").get().addOnSuccessListener {
            protocol = it.value as String
        }

        val backtv: Button = findViewById(R.id.backtv)
        val menu: Button = findViewById(R.id.menu)
        val power: Button = findViewById(R.id.power)
        val source : Button = findViewById(R.id.source)
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
            /*if(_power == "PowerOff")
            dataBase.child("$name/PowerOn").get().addOnSuccessListener {
                mqttClient.Send("TV", it.value.toString()+"/"+protocol+"/"+bitnum)
                _power = "PowerOn"
            }*/
            dataBase.child("$name/power").get().addOnSuccessListener {
                mqttClient.Send("TV", "${it.value.toString()}/$protocol/$bitnum")
                _power = "PowerOff"
            }
        }
        source.setOnClickListener {
            dataBase.child("$name/source").get().addOnSuccessListener {
                mqttClient.Send("TV", "${it.value.toString()}/$protocol/$bitnum")
            }
        }
        chdown.setOnClickListener {
            dataBase.child("$name/chdown").get().addOnSuccessListener {
                mqttClient.Send("TV", "${it.value.toString()}/$protocol/$bitnum")
            }
        }
        chup.setOnClickListener {
            dataBase.child("$name/chup").get().addOnSuccessListener {
                mqttClient.Send("TV", "${it.value.toString()}/$protocol/$bitnum")
            }
        }
        ok.setOnClickListener {
            dataBase.child("$name/ok").get().addOnSuccessListener {
                mqttClient.Send("TV","${it.value.toString()}/$protocol/$bitnum")
            }
        }
        menu.setOnClickListener {
            dataBase.child("$name/menu").get().addOnSuccessListener {
                mqttClient.Send("TV", "${it.value.toString()}/$protocol/$bitnum")
            }
        }
        ttv.setOnClickListener {
            dataBase.child("$name/ttv").get().addOnSuccessListener {
                mqttClient.Send("TV","${it.value.toString()}/$protocol/$bitnum")
            }
        }
        voldown.setOnClickListener {
            dataBase.child("$name/Voldown").get().addOnSuccessListener {
                mqttClient.Send("TV","${it.value.toString()}/$protocol/$bitnum")
            }
        }
        volup.setOnClickListener {
            dataBase.child("$name/Volup").get().addOnSuccessListener {
                mqttClient.Send("TV","${it.value.toString()}/$protocol/$bitnum")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mqttClient.Disconnect()
    }

    override fun onStart() {
        super.onStart()
        Progress.dismiss()
    }
}