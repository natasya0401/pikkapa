package com.pikkapa.view.adapter

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
import com.pikkapa.entity.TutorialEntity

class TutorialItemAdapter(
    private val tutorial: ArrayList<TutorialEntity>,
    val listener: (TutorialEntity) -> Unit
) : RecyclerView.Adapter<TutorialViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_tutorial, parent, false)
        return TutorialViewHolder(v)
    }

    override fun getItemCount(): Int {
        return tutorial.size
    }

    override fun onBindViewHolder(holder: TutorialViewHolder, position: Int) {
        holder.title.text = tutorial[position].title
//        holder.detail.text = tutorial[position].subTitle
        holder.image.clipToOutline = true
        if(tutorial[position].thumbnail.isNullOrEmpty() || tutorial[position].thumbnail.isBlank()) {
            holder.image.setImageResource(R.drawable.ic_image_not_supported)
        } else {
            Glide.with(holder.image.context)
//                .load("https://drive.google.com/uc?id=139jBj_GUfmFi_pZN38SS9RMB5wNXMEy9")
                .load(tutorial[position].thumbnail)
                .placeholder(AppCompatResources.getDrawable(holder.image.context, R.drawable.ic_hourglass_empty))
                .error(R.drawable.ic_image_not_supported)
                .dontTransform()
                .dontAnimate()
                .into(holder.image)
        }

        holder.layout.setOnClickListener {
            listener(tutorial[position])
        }
    }
}

class TutorialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var image: ImageView = itemView.findViewById(R.id.iv_tutorial_image)
    var title: TextView = itemView.findViewById(R.id.tv_tutorial_title)
//    var detail: TextView = itemView.findViewById(R.id.tv_tutorial_detail)
    var layout: CardView = itemView.findViewById(R.id.layout_tutorial)
}