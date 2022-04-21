package com.example.kltn.Model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TVActModel:ViewModel() {
    private val _listTV = MutableLiveData<List<String>>()
    val listTV : LiveData<List<String>>
    get() = _listTV
    fun postList(list:List<String>) {
        _listTV.value = list
    }
}