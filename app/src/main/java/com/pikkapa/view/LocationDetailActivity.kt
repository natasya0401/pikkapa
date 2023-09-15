package com.pikkapa.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.pikkapa.R
import com.pikkapa.databinding.ActivityInformationDetailBinding

class LocationDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInformationDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_location_detail)

        binding.footer.ivBack.setOnClickListener {
            val myIntent = Intent(this, TutorialActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.footer.ivHome.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(myIntent)
        }
    }
}