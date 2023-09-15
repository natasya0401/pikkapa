package com.pikkapa.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.pikkapa.R
import com.pikkapa.databinding.ActivityInformationDetailBinding
import com.pikkapa.databinding.ActivityReminderDetailBinding

class ReminderDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityReminderDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder_detail)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reminder_detail)

        binding.toolbar.tvTitle.text = "REMINDER DETAIL"

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