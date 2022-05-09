package com.example.kltn.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoomModel:ViewModel() {
    private val _listDevice = MutableLiveData<HashMap<String,String>>()
    val listDevice: LiveData<HashMap<String,String>>
    get() = _listDevice

    fun postList(list:HashMap<String,String>) {
        _listDevice.value = list
    }
}