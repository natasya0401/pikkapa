package com.pikkapa.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.pikkapa.R
import com.pikkapa.data.access.ReminderAccess
import com.pikkapa.databinding.ActivityReminderBinding
import com.pikkapa.entity.ReminderEntity
import com.pikkapa.view.adapter.ReminderItemAdapter

class ReminderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReminderBinding
    private var reminders : ArrayList<ReminderEntity> = ArrayList()
    val reminderAccess = ReminderAccess(this)

    override fun onResume() {
        super.onResume()
        reminders = reminderAccess.getAll()
        binding.rvItemReminder.adapter = ReminderItemAdapter(reminders) {
            val myIntent = Intent(this, ReminderDetailActivity::class.java)
            myIntent.putExtra("id", it.id)
            this.startActivity(myIntent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reminder)

        reminders = reminderAccess.getAll()

        //dummy
//        reminders.add(ReminderEntity(1,"Minum Obat","15:00:00", "10-08-2023"))
//        reminders.add(ReminderEntity(1,"Minum Obat 2","15:00:00", "10-08-2023", true))
//        reminders.add(ReminderEntity(1,"Minum Obat 3","15:00:00", "10-08-2023", false, true, "0"))
//        reminders.add(ReminderEntity(1,"Minum Obat 4","15:00:00", "10-08-2023", false, false, "", true, "25"))
//        reminders.add(ReminderEntity(1,"Minum Obat 5","15:00:00", "20-08-2023"))

        setToolbar()
        setAllView()
        setAllOnClick()
    }

    fun setToolbar() {
        binding.toolbar.tvTitle.text = "REMINDER"

        binding.toolbar.ivMenu.setOnClickListener{
            val myIntent = Intent(this, MenuActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.toolbar.ivInfo.setOnClickListener {
            val myIntent = Intent(this, AboutMeActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.footer.ivBack.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.footer.ivHome.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(myIntent)
        }
    }

    fun setAllView() {
        binding.rvItemReminder.layoutManager = LinearLayoutManager(this)
        binding.rvItemReminder.adapter = ReminderItemAdapter(reminders) {
            val myIntent = Intent(this, ReminderDetailActivity::class.java)
            myIntent.putExtra("id", it.id)
            this.startActivity(myIntent)
        }
    }

    fun setAllOnClick() {
        binding.btReminderAdd.setOnClickListener {
            val myIntent = Intent(this, ReminderAddActivity::class.java)
            this.startActivity(myIntent)
        }
    }
}