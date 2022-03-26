package com.example.kltn

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LearnModel:ViewModel() {
    private val _code = MutableLiveData<String>()
    val code : LiveData<String>
    get() = _code

    public fun addCode(topic:String,code:String){
        if(topic == "Learn"){
            _code.postValue(code);
        }
        else _code.postValue("")
    }
}