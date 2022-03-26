package com.example.kltn

import com.hivemq.client.mqtt.MqttClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5BlockingClient
import java.nio.charset.StandardCharsets.UTF_8


object MqttConnect  {
    private final val host :String = "fd3b3ad84b6246349a56fabb5dfbc4dc.s1.eu.hivemq.cloud"
    private final val username :String = "nvt1011"
    private final val password : String = "Nvt101120@"
    private final val client : Mqtt5BlockingClient = MqttClient.builder()
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
        client.subscribeWith()
            .topicFilter(topic)
            .send();
        return client
    }
    public fun Send(topic:String,message:String) {
        client.publishWith()
            .topic(topic)
            .payload(UTF_8.encode(message))
            .send();
    }
}