package com.example.kltn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LearnModel:ViewModel() {
    private val _recvBuf = MutableLiveData<String>()
    val recvBuf : LiveData<String>
        get() = _recvBuf
    fun addCode(buffer : String)
    {
        _recvBuf.postValue(buffer);
    }
}