package com.pikkapa.view

import android.Manifest
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.NotificationVisibility
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.pikkapa.R
import com.pikkapa.data.access.ReminderAccess
import com.pikkapa.databinding.ActivityReminderAddBinding
import com.pikkapa.entity.ReminderEntity
import com.pikkapa.tools.alarm.AlarmItem
import com.pikkapa.tools.alarm.AlarmReceiver
import com.pikkapa.tools.alarm.AndroidAlarmScheduler
import com.pikkapa.tools.dialog.DialogBase
import java.text.SimpleDateFormat
//import com.plcoding.alarmmanagerguide.AlarmItem
//import com.plcoding.alarmmanagerguide.AlarmReceiver
import java.util.*
import kotlin.collections.ArrayList


class ReminderAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReminderAddBinding
    private var timePick: String = ""
    private var datePick: String = ""
    private var repeatePick: String = ""

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder_add)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_reminder_add)

        binding.toolbar.tvTitle.text = "TAMBAH REMINDER"

        //set repeat picker
        binding.tvReminderRepeatPicker.text="SATU KALI"
        repeatePick="SATU KALI"
        val popup = PopupMenu(this, binding.ivReminderRepeatPicker)
        popup.inflate(R.menu.menu_reminder_repeat)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.mi_repeat_once -> {
                    binding.tvReminderRepeatPicker.text="SATU KALI"
                    repeatePick="SATU KALI"
                    binding.tvReminderDate.visibility = View.VISIBLE
                    binding.tvReminderDatePicker.visibility = View.VISIBLE
                    return@setOnMenuItemClickListener true
                }
                R.id.mi_repeat_daily -> {
                    binding.tvReminderRepeatPicker.text="SETIAP HARI"
                    repeatePick="SETIAP HARI"
                    binding.tvReminderDate.visibility = View.GONE
                    binding.tvReminderDatePicker.visibility = View.GONE
                    return@setOnMenuItemClickListener true
                }
                R.id.mi_repeat_monday -> {
                    binding.tvReminderRepeatPicker.text="SETIAP SENIN"
                    repeatePick="SETIAP SENIN"
                    binding.tvReminderDate.visibility = View.GONE
                    binding.tvReminderDatePicker.visibility = View.GONE
                    return@setOnMenuItemClickListener true
                }
                R.id.mi_repeat_tuesday -> {
                    binding.tvReminderRepeatPicker.text="SETIAP SELASA"
                    repeatePick="SETIAP SELASA"
                    binding.tvReminderDate.visibility = View.GONE
                    binding.tvReminderDatePicker.visibility = View.GONE
                    return@setOnMenuItemClickListener true
                }
                R.id.mi_repeat_wednesday -> {
                    binding.tvReminderRepeatPicker.text="SETIAP RABU"
                    repeatePick="SETIAP RABU"
                    binding.tvReminderDate.visibility = View.GONE
                    binding.tvReminderDatePicker.visibility = View.GONE
                    return@setOnMenuItemClickListener true
                }
                R.id.mi_repeat_thursday -> {
                    binding.tvReminderRepeatPicker.text="SETIAP KAMIS"
                    repeatePick="SETIAP KAMIS"
                    binding.tvReminderDate.visibility = View.GONE
                    binding.tvReminderDatePicker.visibility = View.GONE
                    return@setOnMenuItemClickListener true
                }
                R.id.mi_repeat_friday -> {
                    binding.tvReminderRepeatPicker.text="SETIAP JUMAT"
                    repeatePick="SETIAP JUMAT"
                    binding.tvReminderDate.visibility = View.GONE
                    binding.tvReminderDatePicker.visibility = View.GONE
                    return@setOnMenuItemClickListener true
                }
                R.id.mi_repeat_saturday -> {
                    binding.tvReminderRepeatPicker.text="SETIAP SABTU"
                    repeatePick="SETIAP SABTU"
                    binding.tvReminderDate.visibility = View.GONE
                    binding.tvReminderDatePicker.visibility = View.GONE
                    return@setOnMenuItemClickListener true
                }
                R.id.mi_repeat_sunday -> {
                    binding.tvReminderRepeatPicker.text="SETIAP MINGGU"
                    repeatePick="SETIAP MINGGU"
                    binding.tvReminderDate.visibility = View.GONE
                    binding.tvReminderDatePicker.visibility = View.GONE
                    return@setOnMenuItemClickListener true
                }
                else -> false
            }
        }

        binding.ivReminderRepeatPicker.setOnClickListener{
            popup.show()
        }

        binding.footer.ivBack.setOnClickListener {
            val myIntent = Intent(this, ReminderActivity::class.java)
            this.startActivity(myIntent)
        }

        binding.footer.ivHome.setOnClickListener {
            val myIntent = Intent(this, HomeActivity::class.java)
            this.startActivity(myIntent)
        }


        //set time picker
        var onTimeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
            var jam = "$i"
            var menit = "$i2"

            if(i<10) {
                jam = "0$i"
            }

            if(i2<10) {
                menit = "0$i2"
            }

            timePick = "$jam:$menit"

            binding.btReminderTimePicker.text = timePick
        }

        var timePickerDialog = TimePickerDialog(this, onTimeSetListener, 0, 0, true)
        timePickerDialog.setTitle("Pilih Waktu")

        binding.btReminderTimePicker.setOnClickListener {
            timePickerDialog.show()
        }


        //set date picker
        var sdf = SimpleDateFormat("yyyy-MM-dd")
        datePick = sdf.format(Date())
        binding.tvReminderDatePicker.text = datePick
        var onDateSetListener = DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
            datePick = "$i-${i2+1}-$i3"
            binding.tvReminderDatePicker.text = datePick
        }

        var calendar = Calendar.getInstance()
        calendar.time = Date()

        var datePickerDialog = DatePickerDialog(this, onDateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
        datePickerDialog.setTitle("Pilih Tanggal")

        binding.tvReminderDatePicker.setOnClickListener {
            datePickerDialog.show()
        }


        //set button click
        binding.btReminderCancel.setOnClickListener {
            var dialog = DialogBase(this, "Yakin ingin hapus?", "HAPUS", "BATAL") {
                this@ReminderAddActivity.finish()
                it.dismiss()
            }
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()
        }

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            createNotificationChannel()
//        }

//        binding.btReminderSave.setOnClickListener { scheduleNotification() }


        binding.btReminderSave.setOnClickListener {
            var title = binding.etReminderTitle.text.toString()
            var notes = binding.etReminderNotes.text.toString()

//            val myIntent = Intent(this, ReminderDetailActivity::class.java)
//            myIntent.putExtra("title", title)
//            myIntent.putExtra("notes", notes)

            if(title.isBlank() || title.isNullOrEmpty()) {
                Toast.makeText(this, "Anda belum menuliskan judul", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if(notes.isBlank() || notes.isNullOrEmpty()) {
                notes = title
            }

            if(timePick.equals("")) {
                Toast.makeText(this, "Anda belum memilih jam", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val permissionState =
                ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
            if (permissionState == PackageManager.PERMISSION_DENIED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
                return@setOnClickListener
            }

            val reminderAccess = ReminderAccess(this)

            var latestAlarmId = reminderAccess.getLatestId()

//            if(latestAlarmId==0) {
//                Toast.makeText(this, "Gagal membuat alarm", Toast.LENGTH_LONG)
//                return@setOnClickListener
//            }

            latestAlarmId+=1

            var alarmScheduler = AndroidAlarmScheduler(this)
            var alarmItem = AlarmItem(latestAlarmId, timePick, datePick, repeatePick, title, notes)

            var data = ReminderEntity(
                latestAlarmId,
                title,
                notes,
                timePick,
                datePick,
                repeatePick.equals("SETIAP HARI"),
                !repeatePick.equals("SETIAP HARI") && !repeatePick.equals("SATU KALI"),
                if(!repeatePick.equals("SETIAP HARI") && !repeatePick.equals("SATU KALI")) repeatePick.split(" ")[1] else "",
                false,
                arrayListOf(""),
                arrayListOf("$latestAlarmId")
            )

            var dialog = DialogBase(this, "Yakin ingin simpan?", "SIMPAN", "BATAL") {
                //simpan reminder
                if(repeatePick.equals("SATU KALI")) alarmScheduler.schedule(alarmItem, false)
                else alarmScheduler.schedule(alarmItem, true)

                reminderAccess.insert(data)

                this@ReminderAddActivity.finish()
                it.dismiss()
            }
            dialog.setCanceledOnTouchOutside(true)
            dialog.show()
        }
    }
}