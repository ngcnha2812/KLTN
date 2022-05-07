package com.example.kltn

import android.app.ProgressDialog
import android.content.Context

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
