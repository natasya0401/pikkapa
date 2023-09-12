package com.pikkapa.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.pikkapa.R
import com.pikkapa.entity.LocationEntity

class LocationItemAdapter(
    private val reminder: ArrayList<LocationEntity>,
    val listener: (LocationEntity) -> Unit
) : RecyclerView.Adapter<LocationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_location, parent, false)
        return LocationViewHolder(v)
    }

    override fun getItemCount(): Int {
        return reminder.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        var item = reminder[position]
        holder.title.text = "${item.title} (${item.contactPerson})"
        holder.detail.text = item.address

        holder.layout.setOnClickListener {
            listener(item)
        }
    }
}

class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.tv_location_title)
    var detail: TextView = itemView.findViewById(R.id.tv_location_detail)
    var layout: ConstraintLayout = itemView.findViewById(R.id.layout_location)
}