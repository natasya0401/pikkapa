package com.pikkapa.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.pikkapa.R
import com.pikkapa.databinding.ActivityLocationBinding
import com.pikkapa.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_menu)

        binding.menuBack.setOnClickListener {
            this.finish()
        }

        binding.layoutMenuInformation.setOnClickListener {
            val myIntent = Intent(this, InformationActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.layoutMenuTutorial.setOnClickListener {
            val myIntent = Intent(this, TutorialActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.layoutMenuReminder.setOnClickListener {
            val myIntent = Intent(this, ReminderActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.layoutMenuLokasi.setOnClickListener {
            val myIntent = Intent(this, LocationActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.layoutMenuContact.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=+6281944864620"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}