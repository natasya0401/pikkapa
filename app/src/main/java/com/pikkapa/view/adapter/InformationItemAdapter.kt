package com.pikkapa.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pikkapa.R
import com.pikkapa.domain.InformationEntity
import com.pikkapa.view.MenuActivity

class InformationItemAdapter(
    private val information: ArrayList<InformationEntity>,
    private val isVertical: Boolean,
    val listener: (InformationEntity) -> Unit
): RecyclerView.Adapter<InformationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InformationViewHolder {
        if(isVertical) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_information, parent, false)
            return InformationViewHolder(v)
        } else {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.item_information_mini, parent, false)
            return InformationViewHolder(v)
        }
    }

    override fun getItemCount(): Int {
        return information.size
    }

    override fun onBindViewHolder(holder: InformationViewHolder, position: Int) {
        holder.title.text = information[position].title
        holder.detail.text = information[position].detail
        holder.image.clipToOutline = true
        if(information[position].image.isNullOrEmpty() || information[position].image.isBlank()) {
            holder.image.setImageResource(R.drawable.ic_image_not_supported)
        } else {
            Glide.with(holder.image.context)
//                .load("https://drive.google.com/uc?id=139jBj_GUfmFi_pZN38SS9RMB5wNXMEy9")
                .load(information[position].image)
                .placeholder(AppCompatResources.getDrawable(holder.image.context, R.drawable.ic_hourglass_empty))
                .error(R.drawable.ic_image_not_supported)
                .dontTransform()
                .dontAnimate()
                .into(holder.image)
        }

        holder.layout.setOnClickListener {
            listener(information[position])
        }
    }
}

class InformationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image: ImageView = itemView.findViewById(R.id.iv_information_image)
    var title: TextView = itemView.findViewById(R.id.tv_information_title)
    var detail: TextView = itemView.findViewById(R.id.tv_information_detail)
    var layout: CardView = itemView.findViewById(R.id.layout_information)
}