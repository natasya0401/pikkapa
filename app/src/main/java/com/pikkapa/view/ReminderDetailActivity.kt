package com.pikkapa.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.pikkapa.R
import com.pikkapa.data.access.ReminderAccess
import com.pikkapa.databinding.ActivityInformationDetailBinding
import com.pikkapa.databinding.ActivityReminderDetailBinding
import com.pikkapa.entity.ReminderEntity

class ReminderDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityReminderDetailBinding
    private lateinit var reminder : ReminderEntity
    val reminderAccess = ReminderAccess(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder_detail)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reminder_detail)

        binding.toolbar.tvTitle.text = "REMINDER DETAIL"

        var id = -1
        if(intent.hasExtra("id")) {
            id = intent?.getIntExtra("id", 0)!!
        }

        if(id!=-1) {
            reminder = reminderAccess.getById(id)
        }


        binding.footer.ivBack.setOnClickListener {
            val myIntent = Intent(this, ReminderActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.footer.ivHome.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(myIntent)
        }
    }
}