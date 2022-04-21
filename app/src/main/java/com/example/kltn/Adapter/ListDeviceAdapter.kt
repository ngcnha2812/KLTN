package com.example.kltn.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kltn.Buttom_TV_Act
import com.example.kltn.TVAct
import com.example.kltn.databinding.ButtonItemBinding

class ListDeviceAdapter: ListAdapter<String, ListDeviceAdapter.ListDevVH>(ListButtonDiffUtilCallBack()) {
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDevVH {
        return ListDevVH.from(parent)
    }

    override fun onBindViewHolder(holder: ListDevVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.binding.TVType.setOnClickListener {
            val intent = Intent(it.context,Buttom_TV_Act::class.java)
            intent.putExtra("name", "TV/$item")
            it.context.startActivity(intent)
        }
    }
}