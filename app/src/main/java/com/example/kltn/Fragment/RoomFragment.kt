package com.example.kltn.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kltn.databinding.RoomFragBinding

class RoomFragment(var roomName:String?=null):Fragment() {
    private lateinit var binding : RoomFragBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RoomFragBinding.inflate(inflater,container,false)
        binding.roomName = roomName
        return binding.root
    }
}