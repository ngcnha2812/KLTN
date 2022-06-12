package com.example.kltn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LearnModel:ViewModel() {
    private val _recvBuf = MutableLiveData<String>()
    val recvBuf : LiveData<String>
        get() = _recvBuf
    private val _manufacturer = MutableLiveData<String>()
    val manufacturer : LiveData<String>
        get() = _manufacturer
    private val _deviceType = MutableLiveData<String>()
    val deviceType : LiveData<String>
        get() = _deviceType
    private val _freq = MutableLiveData<String>()
    val freq : LiveData<String>
        get() = _freq

    fun addCode(buffer : String)
    {
        _recvBuf.postValue(buffer);
    }
}