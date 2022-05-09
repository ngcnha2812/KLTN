package com.example.kltn.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.Button_TV_Act
import com.example.kltn.Constants
import com.example.kltn.Progress
import com.example.kltn.R
import com.example.kltn.databinding.ButtonItemBinding
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ListDeviceAdapter(var extras:String? = null): ListAdapter<String, ListDeviceAdapter.ListDevVH>(ListButtonDiffUtilCallBack()) {
    private val database = Firebase.database(Constants.databaseURL).reference
    class ListButtonDiffUtilCallBack: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
    class ListDevVH private constructor(var binding: ButtonItemBinding):RecyclerView.ViewHolder(binding.root){
        companion object {
            fun from(parent: ViewGroup):ListDevVH{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ButtonItemBinding.inflate(layoutInflater,parent,false)
                return ListDevVH(binding)
            }
        }
        fun bind(item:String){
            binding.tvName = item;
        }
    }
    private val progress = Progress

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDevVH {
        return ListDevVH.from(parent)
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: ListDevVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.TVType.setOnClickListener {
            progress.context = it.context
            progress.create()
            progress.show()
            database.child("ROOM/$extras").setValue(item)
            val intent = Intent(it.context,Button_TV_Act::class.java)
            it.context.startActivity(intent)
        }
    }
}