package com.example.kltn

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Context

@SuppressLint("StaticFieldLeak")
object Progress {
    var context : Context? = null
    lateinit var progress : ProgressDialog
    fun create(){
        progress = ProgressDialog(context,R.layout.loading)
    }
    fun show(){
        progress.show()
    }
    fun dismiss(){
        progress.dismiss()
    }
}
