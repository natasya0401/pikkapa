package com.pikkapa.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.pikkapa.R
import com.pikkapa.databinding.ActivityAboutMeBinding
import com.pikkapa.databinding.ActivityHomeBinding

class AboutMeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutMeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_me)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_me)

        setToolbar()
        setAllView()
        setAllOnClick()
    }

    fun setToolbar() {
        binding.toolbar.tvTitle.text = "ABOUT ME"

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