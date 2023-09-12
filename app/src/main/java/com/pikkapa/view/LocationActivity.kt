package com.pikkapa.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.pikkapa.R
import com.pikkapa.databinding.ActivityHomeBinding
import com.pikkapa.databinding.ActivityLocationBinding

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_location)

        setToolbar()
        setAllView()
        setAllOnClick()
    }

    fun setToolbar() {
        binding.toolbar.tvTitle.text = "LOKASI RS"

        binding.toolbar.ivMenu.setOnClickListener{
            val myIntent = Intent(this, MenuActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.toolbar.ivInfo.setOnClickListener {
            val myIntent = Intent(this, AboutMeActivity::class.java)
            this.startActivity(myIntent)
        }
    }

    fun setAllView() {

    }

    fun setAllOnClick() {

    }
}