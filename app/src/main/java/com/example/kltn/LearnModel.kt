package com.example.kltn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LearnModel:ViewModel() {
    private val _recvStr = MutableLiveData<String>()
    val recvStr : LiveData<String>
        get() = _recvStr
    private val _code = MutableLiveData<String>()
    val code : LiveData<String>
        get() = _code
    private val _manufacturer = MutableLiveData<String>()
    val manufacturer : LiveData<String>
        get() = _manufacturer
    private val _protocol = MutableLiveData<String>()
    val protocol : LiveData<String>
        get() = _protocol
    private val _freq = MutableLiveData<String>()
    val freq : LiveData<String>
        get() = _freq
    private val _bitnum = MutableLiveData<String>()
    val bitnum : LiveData<String>
        get() = _bitnum
    public fun addCode(topic:String,code:String){
        if(topic == "Learn"){
            _recvStr.postValue(code);
        }
        else _recvStr.postValue("")
        splitString();
    }
    private fun splitString(){
        var recvArr = listOf<String>()
        if(_recvStr.value != "" &&_recvStr.value != null)  recvArr = _recvStr.value?.split("/")!!
        if(recvArr.isNotEmpty()) {
            Log.d("check","$recvArr")
            _code.postValue(recvArr[0])
            _protocol.postValue(recvArr[1])
            _bitnum.postValue(recvArr[2])
            if(recvArr.size == 4) _freq.postValue(recvArr[3])
        }
    }
}