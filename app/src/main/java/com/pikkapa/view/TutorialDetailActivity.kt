package com.pikkapa.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.pikkapa.R
import com.pikkapa.databinding.ActivityTutorialDetailBinding


class TutorialDetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTutorialDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial_detail)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tutorial_detail)

        binding.toolbar.tvTitle.text = "TUTORIAL DETAIL"

        var data = "Your data which you want to load"

        if (intent.hasExtra("url")) {
            data = intent.getStringExtra("url").toString()
            Log.d("html", data)
        }

        if(intent.hasExtra("title")) {
            binding.tvTutorialTitle.text = intent.getStringExtra("title")
            Log.d("html", "ada title")
        }

        if(intent.hasExtra("subtitle")) {
            binding.tvTutorialDetail.text = intent.getStringExtra("subtitle")
            Log.d("html", "ada title")
        }

//        Log.d("html", data)
//        val webview = findViewById<View>(R.id.webview_tutorial) as WebView
//        webview.settings.javaScriptEnabled = true
//        webview.loadUrl(data)
//        webview.loadData(data, "text/html; charset=utf-8", "UTF-8")

        //build your own src link with your video ID
        //build your own src link with your video ID
        val videoStr =
            "<html><body><iframe width=\"380\" height=\"315\" src=\"$data\" frameborder=\"0\" allowfullscreen></iframe></body></html>"

        binding.webviewTutorial.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                return false
            }
        })

        binding.footer.ivBack.setOnClickListener {
            val myIntent = Intent(this, TutorialActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.footer.ivHome.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.webviewTutorial.getSettings().javaScriptEnabled = true
        binding.webviewTutorial.loadData(videoStr, "text/html", "utf-8")
    }
}