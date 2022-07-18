package com.example.kltn

import android.util.Log
import androidx.annotation.NonNull
import com.example.kltn.Model.DetailModel
import com.google.common.eventbus.Subscribe
import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.MqttGlobalPublishFilter
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient
import org.json.JSONArray
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets.UTF_8
import java.util.function.Consumer


object MqttConnect  {
    private final val host :String = "fd3b3ad84b6246349a56fabb5dfbc4dc.s1.eu.hivemq.cloud"
    private final val username :String = "nvt1011"
    private final val password : String = "Nvt101120"
    private val client : Mqtt5BlockingClient = MqttClient.builder()
        .useMqttVersion5()
        .serverHost(host)
        .serverPort(8883)
        .sslWithDefaultConfig()
        .buildBlocking()
    public fun Connect(topic:String) : MqttClient{
        client.connectWith()
            .simpleAuth()
            .username(username)
            .password(UTF_8.encode(password))
            .applySimpleAuth()
            .send();
        return client
    }
    fun Subscribe(topic:String)
    {
        client.subscribeWith()
            .topicFilter(topic)
            .send();
    }

    public fun Send(topic:String,message:ByteArray) {
        client.publishWith()
            .topic(topic)
            .payload(message)
            .send();
    }
    public fun Async(model:LearnModel){
        client.toAsync().publishes(MqttGlobalPublishFilter.ALL, Consumer {
            Log.d("check", it.payloadAsBytes.toString())
            model.addCode(JSONArray(it.payloadAsBytes).toString());
        })
    }

    public fun Async(model:DetailModel){
        var Detail = ""
        Log.d("check", "fggfh")
        client.toAsync().publishes(MqttGlobalPublishFilter.ALL, Consumer {
            System.out.println("Received message: " + it.getTopic() + " -> " + UTF_8.decode(it.getPayload().get()));
            model.getDetail(UTF_8.decode(it.getPayload().get()).toString())
            })
    }
    public fun Disconnect(){
        client.disconnect()
    }
}