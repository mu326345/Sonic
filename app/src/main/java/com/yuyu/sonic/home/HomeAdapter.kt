package com.yuyu.sonic.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yuyu.sonic.data.FlightDataItem
import com.yuyu.sonic.databinding.ItemFlightBinding

class HomeAdapter(val clickListener: (flightDat: FlightDataItem) -> Unit) : ListAdapter<FlightDataItem, HomeAdapter.HomeViewHolder>(DiffCallback) {

    class HomeViewHolder(private val binding: ItemFlightBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: FlightDataItem) {
            binding.flight = data
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(ItemFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        holder.itemView.setOnClickListener {
            clickListener(item)
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<FlightDataItem>() {
        override fun areItemsTheSame(oldItem: FlightDataItem, newItem: FlightDataItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FlightDataItem, newItem: FlightDataItem): Boolean {
            return oldItem == newItem
        }
    }
}