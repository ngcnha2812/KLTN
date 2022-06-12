package com.example.kltn.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.ButtonFan
import com.example.kltn.Button_TV_Act
import com.example.kltn.Constants
import com.example.kltn.Progress
import com.example.kltn.databinding.RoomDeviceItemBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.collections.Map

class RoomDeviceAdapter : ListAdapter<Map.Entry<String, String>, RoomDeviceAdapter.RoomDevVH>(ListButtonDiffUtilCallBack()) {
    private val database = Firebase.database(Constants.databaseURL).reference
    class ListButtonDiffUtilCallBack: DiffUtil.ItemCallback<Map.Entry<String,String>>() {
        override fun areItemsTheSame(oldItem: Map.Entry<String,String>, newItem: Map.Entry<String,String>): Boolean {
            return oldItem.key == newItem.key
        }

        override fun areContentsTheSame(oldItem: Map.Entry<String,String>, newItem: Map.Entry<String,String>): Boolean {
            return oldItem == newItem
        }

    }
    class RoomDevVH private constructor(var binding: RoomDeviceItemBinding): RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup):RoomDevVH{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RoomDeviceItemBinding.inflate(layoutInflater,parent,false)
                return RoomDevVH(binding)
            }
        }
        fun bind(item: Map.Entry<String, String>){
            binding.device.text = item.key
            binding.manft.text = item.value
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomDevVH {
        return RoomDevVH.from(parent)
    }

    override fun onBindViewHolder(holder: RoomDevVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.roomDevice.setOnClickListener {
            var intent = Intent()
            when (item.key) {
                "TV" -> intent = Intent(it.context,Button_TV_Act::class.java)
                "FAN" ->  intent = Intent(it.context,ButtonFan::class.java)
                //"AC" ->  intent = Intent(it.context,ButtonAC::class.java)
            }
            intent.putExtra("PATH","${item.key.uppercase()}/${item.value.uppercase()}")
            it.context.startActivity(intent)
        }
    }
}