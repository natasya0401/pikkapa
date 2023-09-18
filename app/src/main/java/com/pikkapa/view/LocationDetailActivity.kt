package com.pikkapa.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.pikkapa.R
import com.pikkapa.databinding.ActivityInformationDetailBinding
import com.pikkapa.databinding.ActivityLocationDetailBinding
import kotlin.math.log

class LocationDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLocationDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_location_detail)


        binding.footer.ivBack.setOnClickListener {
            val myIntent = Intent(this, TutorialActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.toolbar.tvTitle.text = "LOKASI RS"

        binding.footer.ivHome.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(myIntent)
        }
        var lgtd = "-7.99409950053184"
        var lttd = "112.63405809538726"


        if (intent.hasExtra("title")) {
            binding.tvLocationTitle.text = intent.getStringExtra("title")
            Log.d("title", "ada title")
        }

        if (intent.hasExtra("address")) {
            binding.tvLocationDetail.text = intent.getStringExtra("address")
            Log.d("address", "ada address")
        }

        if (intent.hasExtra("contactPerson")) {
            binding.tvLocationCp.text = intent.getStringExtra("contactPerson")
            Log.d("contactPerson", "ada CP")
        }

        if (intent.hasExtra("longitude")) {
            lgtd = intent.getStringExtra("longitude").toString()
            Log.d("longitude", lgtd)
        }

        if (intent.hasExtra("latitude")) {
            lttd = intent.getStringExtra("latitude").toString()
            Log.d("longitude", lttd)
        }

        val maps = findViewById<View>(R.id.layout_maps)
    }
}