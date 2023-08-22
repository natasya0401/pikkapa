package com.pikkapa.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pikkapa.R
import com.pikkapa.domain.ReminderEntity
import com.pikkapa.domain.TutorialEntity

class ReminderItemAdapter(
    private val reminder: ArrayList<ReminderEntity>,
    val listener: (ReminderEntity) -> Unit
) : RecyclerView.Adapter<ReminderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_reminder, parent, false)
        return ReminderViewHolder(v)
    }

    override fun getItemCount(): Int {
        return reminder.size
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        var item = reminder[position]
        holder.title.text = item.title
        holder.time.text = item.time

        if(item.repeatDaily) {
            holder.repeat.text = "Setiap Hari"
        } else if (item.repeatWeekly) {
            when (item.repeatWeeklyDay) {
                "0" -> holder.repeat.text = "Setiap Hari Senin"
                "1" -> holder.repeat.text = "Setiap Hari Selasa"
                "2" -> holder.repeat.text = "Setiap Hari Rabu"
                "3" -> holder.repeat.text = "Setiap Hari Kamis"
                "4" -> holder.repeat.text = "Setiap Hari Jumat"
                "5" -> holder.repeat.text = "Setiap Hari Sabtu"
                "6" -> holder.repeat.text = "Setiap Hari Minggu"
            }
        } else if (item.repeatMonthly) {
            holder.repeat.text = "Setiap tanggal ${item.repeatMothlyDate}"
        } else {
            holder.repeat.text = item.date
        }

        holder.layout.setOnClickListener {
            listener(item)
        }
    }
}

class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var title: TextView = itemView.findViewById(R.id.tv_reminder_title)
    var time: TextView = itemView.findViewById(R.id.tv_reminder_time)
    var repeat: TextView = itemView.findViewById(R.id.tv_reminder_repeat)
    var layout: ConstraintLayout = itemView.findViewById(R.id.layout_reminder)
}