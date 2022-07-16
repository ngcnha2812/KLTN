package com.example.kltn.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailModel:ViewModel() {
    private val _detail =  MutableLiveData<String>()
    val detail : LiveData<String>
    get() = _detail

    fun getDetail(detail:String)
    {
        _detail.postValue(detail)
    }
}