package com.example.kltn.Fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kltn.Adapter.RoomDeviceAdapter
import com.example.kltn.Constants
import com.example.kltn.Model.RoomModel
import com.example.kltn.databinding.RoomFragBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RoomFragment(var roomName:String?=null):Fragment() {
    private lateinit var binding : RoomFragBinding
    private lateinit var model : RoomModel
    private lateinit var adapter : RoomDeviceAdapter
    private val database = Firebase.database(Constants.databaseURL).reference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RoomFragBinding.inflate(inflater,container,false)
        //binding.roomName = roomName
        model = RoomModel()
        adapter = RoomDeviceAdapter()
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database.child("ROOM/${roomName?.uppercase()}").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var listDevice = HashMap<String,String>()
                if(snapshot.value != null) listDevice = snapshot.value as HashMap<String, String>
                model.postList(listDevice)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        model.listDevice.observe(viewLifecycleOwner, Observer {
            val list = ArrayList<Map.Entry<String,String>>()
            it.forEach {
                list.add(it)
            }
            adapter.submitList(list)
            binding.listDevice.adapter = adapter
            val lm = LinearLayoutManager(context)
            binding.listDevice.layoutManager = lm
        })
    }
}