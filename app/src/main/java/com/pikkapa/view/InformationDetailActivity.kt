package com.pikkapa.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pikkapa.R
import com.pikkapa.databinding.ActivityInformationDetailBinding
import com.pikkapa.entity.InformationEntity


class InformationDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityInformationDetailBinding
    private lateinit var informations : InformationEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information_detail)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_information_detail)

//        var data = "Your data which you want to load"
        var web = "xxx"

//        if (intent.hasExtra("html")) {
//            data = intent.getStringExtra("html").toString()
//            Log.d("html", data)
//        }

        if (intent.hasExtra("url")) {
            web = intent.getStringExtra("url").toString()
            Log.d("url", web)
        }

//        if(intent.hasExtra("title")) {
        binding.toolbar.tvTitle.text = "DETAIL INFORMASI"
//            binding.toolbar.tvTitle.text = intent.getStringExtra("title")
//            Log.d("html", "ada title")
//        }


        val webStr =
            "<html><body><iframe width=\"380\" height=\"1000\" src=\"$web\" frameborder=\"0\" allowfullscreen></iframe></body></html>"

        Log.d("url", web)
        val webview = findViewById<View>(R.id.webview) as WebView
        webview.settings.javaScriptEnabled = true
        webview.loadData(webStr, "text/html; charset=utf-8", "UTF-8")
        webview.isVerticalScrollBarEnabled = true;
        webview.isHorizontalScrollBarEnabled = true;
    }
}