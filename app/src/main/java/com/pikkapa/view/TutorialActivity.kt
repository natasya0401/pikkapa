package com.pikkapa.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pikkapa.R
import com.pikkapa.databinding.ActivityTutorialBinding
import com.pikkapa.entity.TutorialEntity
import com.pikkapa.view.adapter.TutorialItemAdapter

class TutorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTutorialBinding
    private var tutorials : ArrayList<TutorialEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_tutorial)

        setToolbar()
        setAllView()
        setAllOnClick()
    }

    fun setToolbar() {
        binding.toolbar.tvTitle.text = "TUTORIAL"

        binding.toolbar.ivMenu.setOnClickListener{
            val myIntent = Intent(this, MenuActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.toolbar.ivInfo.setOnClickListener {
            val myIntent = Intent(this, AboutMeActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.footer.ivBack.setOnClickListener {
//            val myIntent = Intent(this, HomeActivity::class.java)
//            this.startActivity(myIntent)
            onBackPressed()
        }

        binding.footer.ivHome.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(myIntent)
        }
    }

    fun setAllView() {
        //dummy
        tutorials.add(TutorialEntity("Cancer-Fighting Herbs And Spices", "Cancer-Fighting Herbs And Spices", "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEg1TLgQOxqv7o7Z3Lk6xVEmWAdkLqd_q-XC4yOYRDpBynE4PwvwhAixhS5SE5kQw_9ETdLrNGkDwnCUvv1Wu8t1L8girV5S-wtZZKcgPBINuoo3-H5oHRaFs0lxYYNG7AmkEGxjze8NBTGIVAFElivGukJefPbp3UB4RcfbXTvUIs2E-CKQqgVU-mGlUW0/s1600/Screenshot%202023-09-29%20at%2023.31.13.png", "https://www.youtube.com/embed/IXEt_ZPPfjk"))
        tutorials.add(TutorialEntity("Klasifikasi atau Jenis Kanker Payudara", "Klasifikasi atau Jenis Kanker Payudara", "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEjucZDvbEjX9lav6MmBxVXnY7axkS7ixg2t6jy_C03xlb38C6Zr6UlqnfOQSzxcYX0c68ni4hBzsB8iLFP-m2sVGxK0U_bT0xXR7L788mzgLV-GA2_YhvJEUTePIOGDF5YSKyYkloHh4kB7AUKTeNtcRgX_xQSEcmWwWvkYPlr1_npygjmXs988A1_AwME/s1600/Screenshot%202023-09-29%20at%2023.33.29.png", "https://www.youtube.com/embed/2iOfV5kM6yM"))
        tutorials.add(TutorialEntity("Diagnosis dan Terapi Kanker Payudara Stadium Dini", "Diagnosis dan Terapi Kanker Payudara Stadium Dini\\n", "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEh8BklbAb_5ydOy0Pbw6dcfp49DBc-ogDwcer8NVGUVn_AT0_A3-A5JMTbFEeyiMjiOBxNKII5V_ZD1UDoRDsTSG7uiXvfzZuonh390vCDJWuNqmtq-ZWC6ojTWNQc4qbsDgyxJcqoBe3QTW8KnaAOP-nXBACDU3F0LeTjVSII4GlCdppublYwuEt3MZtg/s1600/Screenshot%202023-09-29%20at%2023.40.48.png", "https://www.youtube.com/embed/5A5dQnSPRFE"))
        tutorials.add(TutorialEntity("Tatalaksana Kanker Payudara Stadium Lanjut (Advance Breast Cancer/ABC)\\n", "Tatalaksana Kanker Payudara Stadium Lanjut (Advance Breast Cancer/ABC)", "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEgkiRe5GstKLUeCxZhnDqHswg6gf5gW5K2VyvEUi59WevdxPBagpOOP6YNiX3SM6hNmzuFaYsGUfvyELB-2zu2wlEcItCsNjT6y_trBlUYkYPhOigTw8qknM_M_kfliC4Dfm5q8k3Fl1mmbgNoSWeaixfK7DONTkomKO_JoT9G3U7AivQ3zB87xmq16YfI/s1600/Screenshot%202023-09-29%20at%2023.35.19.png", "https://www.youtube.com/embed/nbIbSOCfU7E"))
        tutorials.add(TutorialEntity("TOP 10 Natural Foods Proven to ANTI CANCER that You Must Eat Every Day ", "TOP 10 Natural Foods Proven to ANTI CANCER that You Must Eat Every Day ", "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhGW25arC4L68pMkZU8lXNJ2j3RfhufcCkfEgdePGWQtWQmVJd9Qa_itEqC-YXQrtCy2K1Azw3htUHmVnARAsgArpQhDjMbYCYPk_Er8rn7LA-W7w0J4CyBzxRUyN3czyVgGbnCwk-v9O2m5Pi763SVtRvCHLUX12mciZy93mMVaF-Av3cT6tAPJ4za9Lk/s1600/Screenshot%202023-09-29%20at%2023.26.04.png", "https://www.youtube.com/embed/FGnWNUilZoY"))

        binding.rvItemTutorial.layoutManager = LinearLayoutManager(this)
        binding.rvItemTutorial.adapter = TutorialItemAdapter(tutorials) {
            val myIntent = Intent(this, TutorialDetailActivity::class.java)
            myIntent.putExtra("title", it.title)
            myIntent.putExtra("subtitle", it.subTitle)
            myIntent.putExtra("url", it.youtubeUrl)
            this.startActivity(myIntent)
        }
    }

    fun setAllOnClick() {

    }
}