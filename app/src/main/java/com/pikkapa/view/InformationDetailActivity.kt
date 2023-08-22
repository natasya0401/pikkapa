package com.pikkapa.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pikkapa.R
import com.pikkapa.databinding.ActivityInformationDetailBinding
import com.pikkapa.domain.InformationEntity
import kotlin.math.log


class InformationDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInformationDetailBinding
    private lateinit var informations : InformationEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_detail)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_information_detail)

        var data = "Your data which you want to load"

        if (intent.hasExtra("html")) {
            data = intent.getStringExtra("html").toString()
            Log.d("html", data)
        }

//        if(intent.hasExtra("title")) {
        binding.toolbar.tvTitle.text = "DETAIL INFORMASI"
//            binding.toolbar.tvTitle.text = intent.getStringExtra("title")
//            Log.d("html", "ada title")
//        }

        Log.d("html", data)
        val webview = findViewById<View>(R.id.webview) as WebView
        webview.settings.javaScriptEnabled = true
        webview.loadData(data, "text/html; charset=utf-8", "UTF-8")
        webview.isVerticalScrollBarEnabled = true;
        webview.isHorizontalScrollBarEnabled = true;
    }
}