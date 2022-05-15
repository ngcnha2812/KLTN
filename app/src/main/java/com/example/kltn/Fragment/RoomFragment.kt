package com.example.kltn.Fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.kltn.Constants
import com.example.kltn.Menu
import com.example.kltn.Model.RoomModel
import com.example.kltn.R
import com.example.kltn.databinding.RoomFragBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RoomFragment(var roomName:String?=null):Fragment() {
    private lateinit var binding : RoomFragBinding
    private lateinit var model : RoomModel
    private val database = Firebase.database(Constants.databaseURL).reference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RoomFragBinding.inflate(inflater,container,false)
        binding.roomName = roomName
        model = RoomModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        database.child("ROOM/${roomName?.uppercase()}").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var listDevice = HashMap<String,String>()
                Log.d("check","${snapshot.value}")
                if(snapshot.value != null) listDevice = snapshot.value as HashMap<String, String>
                model.postList(listDevice)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}