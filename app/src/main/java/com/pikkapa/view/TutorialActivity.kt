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
    }

    fun setAllView() {
        //dummy
        tutorials.add(TutorialEntity("tutorial 1", "menjelaskan mengenai cara melakukan", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51", "https://www.youtube.com/embed/LqME1y6Mlyg"))
        tutorials.add(TutorialEntity("tutorial 2", "menjelaskan mengenai cara melakukan", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51", "https://www.youtube.com/embed/LqME1y6Mlyg"))
        tutorials.add(TutorialEntity("tutorial 3", "menjelaskan mengenai cara melakukan", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51", "https://www.youtube.com/embed/LqME1y6Mlyg"))
        tutorials.add(TutorialEntity("tutorial 4", "menjelaskan mengenai cara melakukan", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51", "https://www.youtube.com/embed/LqME1y6Mlyg"))
        tutorials.add(TutorialEntity("tutorial 5", "menjelaskan mengenai cara melakukan", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51", "https://www.youtube.com/embed/LqME1y6Mlyg"))

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