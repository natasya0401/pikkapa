package com.pikkapa.tools.alarm

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.pikkapa.R
import com.pikkapa.data.access.ReminderAccess
import com.pikkapa.view.ReminderActivity
import kotlin.concurrent.timer

class AlarmReceiver: BroadcastReceiver() {

    lateinit var context : Context
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("alarm", "alarm triggered 1")
        if(context!=null) {
            this.context = context
            val title = intent?.getStringExtra("title") ?: return
            val message = intent?.getStringExtra("message") ?: return
            val id = intent?.getStringExtra("alarmId") ?: return


            createNotificationChanel("reminder")
            sendNotification(title, "reminder", id.toInt(), message, intent)
            println("Alarm triggered: $message")
            Log.d("alarm", "alarm triggered $message")

            val reminderAccess = ReminderAccess(this.context)
            reminderAccess.delete(id.toInt())


        }
    }

    private fun createNotificationChanel(chanel_id:String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val description_text = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val chanel : NotificationChannel = NotificationChannel(chanel_id, name, importance).apply {
                description = description_text

            }
            val notificationManager : NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(chanel)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun sendNotification(title:String, chanel_id:String, notif_id:Int, notes:String, intent: Intent){
        val intent = Intent(context, ReminderActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val pendingIntent : PendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
        } else{
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val bigText : NotificationCompat.BigTextStyle = NotificationCompat.BigTextStyle()
        bigText.bigText(notes)
        bigText.setBigContentTitle(title)
        bigText.setSummaryText(notes)

        val builder = NotificationCompat.Builder(context, chanel_id)
            .setSmallIcon(R.drawable.icon_home)
            .setContentTitle(title)
            .setContentText(notes)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setStyle(bigText)
            .setContentIntent(pendingIntent)
            .setChannelId(chanel_id)
        with(NotificationManagerCompat.from(context)){
            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(notif_id, builder.build())
        }
    }

    fun getNextNotificationId(context: Context) : Int {
        val sp = context.getSharedPreferences("your_shared_preferences_key", MODE_PRIVATE)
        val id = sp.getInt("notification_id_key", 0)
        sp.edit().putInt("notification_id_key", (id + 1) % Int.MAX_VALUE).apply()

        return id
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setRepeatingDaily(androidAlarmScheduler: AndroidAlarmScheduler) {

        val calendar = Calendar.getInstance()

        calendar.add(Calendar.DAY_OF_YEAR, 1)

        val time = calendar.timeInMillis

        androidAlarmScheduler.setRepeatDaily(time)

    }
}