package com.pikkapa.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pikkapa.R
import com.pikkapa.databinding.ActivityHomeBinding
import com.pikkapa.entity.InformationEntity
import com.pikkapa.entity.ReminderEntity
import com.pikkapa.view.adapter.InformationItemAdapter
import java.text.SimpleDateFormat
import java.util.*


class HomeActivity : AppCompatActivity() {

    private lateinit var binding:ActivityHomeBinding
    private var informations : ArrayList<InformationEntity> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        setToolbar()
        setAllView()
        setAllOnClick()

    }

    fun setToolbar() {
        binding.toolbar.tvTitle.text = "PIKKAPA"

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
        binding.layoutReminder.visibility = View.GONE

        //dummy
        var reminder = ReminderEntity(1, "Minum Obat","","15:00:00", "10-08-2023")

        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
        val reminderDate = sdf.parse("${reminder.date} ${reminder.time}")
        val today = Date()

        var duration = reminderDate.time - today.time
        var countdown = object: CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                var millisUntilFinished = millisUntilFinished
                val secondsInMilli: Long = 1000
                val minutesInMilli = secondsInMilli * 60
                val hoursInMilli = minutesInMilli * 60
                val elapsedHours = millisUntilFinished / hoursInMilli
                millisUntilFinished = millisUntilFinished % hoursInMilli
                val elapsedMinutes = millisUntilFinished / minutesInMilli
                millisUntilFinished = millisUntilFinished % minutesInMilli
                val elapsedSeconds = millisUntilFinished / secondsInMilli
                val hhmmss = String.format("%02d jam %02d menit %02d detik", elapsedHours, elapsedMinutes,elapsedSeconds)
                binding.tvReminderCountdown.text = hhmmss
            }

            override fun onFinish() {
                binding.layoutReminder.visibility = View.GONE
            }
        }
        countdown.start()
        binding.tvReminderTime.text = "${reminder.date} ${reminder.time}"
        binding.tvReminderTitle.text = reminder.title
        binding.layoutReminder.visibility = View.VISIBLE


        //dummy
//        informations.add(InformationEntity("judul 1", "", "gambar 1"))
//        informations.add(InformationEntity("judul 2","", "https://drive.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51"))
//        informations.add(InformationEntity("judul 3", "", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51"))
//        informations.add(InformationEntity("judul 4", "", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51"))

        informations.add(InformationEntity("judul 1", "ini adalah foto orang peace di pantai", "gambar 1", "https://www.lipsum.com/"))
        informations.add(InformationEntity("judul 2","ini adalah foto orang peace di pantai", "https://drive.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51", "https://www.lipsum.com/"))
        informations.add(InformationEntity("judul 3", "ini adalah foto orang peace di pantai", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51", "https://www.lipsum.com/"))
        informations.add(InformationEntity("judul 4", "ini adalah foto orang peace di pantai", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51", "https://www.lipsum.com/"))
        informations.add(InformationEntity("judul 5","ini adalah foto orang peace di pantai", "https://docs.google.com/uc?id=1KVZrjyCNZkf66WdDVBBf3brMH3ca4F51", "https://www.lipsum.com/"))

        binding.rvItemInformation.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        binding.rvItemInformation.adapter = InformationItemAdapter(informations, false) {
//            val myIntent = Intent(this, InformationDetailActivity::class.java)
//            myIntent.putExtra("title", it.title)
//            myIntent.putExtra("html", it.html)
//            myIntent.putExtra("url", it.url)
//            this.startActivity(myIntent)
//        }


    }

    fun setAllOnClick() {
        binding.layoutReminder.setOnClickListener {
            val myIntent = Intent(this, ReminderActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.tvMenuInformation.setOnClickListener {
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

        binding.btChat.setOnClickListener {
            val url = "https://api.whatsapp.com/send?phone=+62818533" +
                    "933"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }

    override fun onStart() {
        super.onStart()
        val permissionState =
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
        if (permissionState == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }
    }

}