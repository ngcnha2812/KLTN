package com.example.kltn.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoomModel:ViewModel() {
    private val _listDevice = MutableLiveData<Map<String,String>>()
    val listDevice: LiveData<Map<String,String>>
    get() = _listDevice

    fun postList(list:Map<String,String>) {
        _listDevice.value = list
    }
}