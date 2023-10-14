package com.pikkapa.tools.alarm

//import com.plcoding.alarmmanagerguide.AlarmItem
//import com.plcoding.alarmmanagerguide.AlarmReceiver
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import java.util.*

class AndroidAlarmScheduler(
    private val context: Context
): AlarmScheduler {
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private var itemId : Int = 0
    override fun schedule(item: AlarmItem, repeat:Boolean) {

        itemId = item.id

//        val intent = getIntent().apply {
//            putExtra("title",item.title)
//            putExtra("message", item.message)
//            putExtra("alarmId", item.id.toString())
//        }

        val pendingIntent = getPendingIntent(getIntent())

//        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            PendingIntent.getBroadcast(context, item.id, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
//        } else{
//            PendingIntent.getBroadcast(context, item.id, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//        }

        var times = item.time.split(":")
        var calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(times[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(times[1]))
        calendar.set(Calendar.SECOND, 0)

        val timeInMillis = calendar.timeInMillis

        if(repeat) {
            when(item.repeat) {
                "SETIAP HARI" -> {

                    if (calendar.time.compareTo(Date()) < 0) calendar.add(Calendar.DAY_OF_MONTH, 1)


                    setRepeatDaily(timeInMillis, getPendingIntent(getIntent().apply {
                        putExtra("title",item.title)
                        putExtra("message", item.message)
                        putExtra("alarmId", item.id.toString())
                        putExtra("r", "1")
                        putExtra("hour", times[0])
                        putExtra("minute", times[1])
                    }))

//                    alarmManager.setRepeating(
//                        AlarmManager.RTC_WAKEUP,
//                        calendar.timeInMillis,
//                        AlarmManager.INTERVAL_DAY,
//                        pendingIntent
//                    )
                }
                "SETIAP SENIN" -> {
//                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,pendingIntent)

                    val cal = checkDayOfWeek(calendar, Calendar.MONDAY)

                    setRepeatWeekly(cal.timeInMillis, getPendingIntent(getIntent().apply {
                        putExtra("title",item.title)
                        putExtra("message", item.message)
                        putExtra("alarmId", item.id.toString())
                        putExtra("r", "2")
                        putExtra("hour", times[0])
                        putExtra("minute", times[1])
                    }))

                }
                "SETIAP SELASA" -> {
                    val cal = checkDayOfWeek(calendar, Calendar.TUESDAY)
                    setRepeatWeekly(cal.timeInMillis, getPendingIntent(getIntent().apply {
                        putExtra("title",item.title)
                        putExtra("message", item.message)
                        putExtra("alarmId", item.id.toString())
                        putExtra("r", "2")
                        putExtra("hour", times[0])
                        putExtra("minute", times[1])
                    }))
                }
                "SETIAP RABU" -> {
                    val cal = checkDayOfWeek(calendar, Calendar.WEDNESDAY)
                    setRepeatWeekly(cal.timeInMillis, getPendingIntent(getIntent().apply {
                        putExtra("title",item.title)
                        putExtra("message", item.message)
                        putExtra("alarmId", item.id.toString())
                        putExtra("r", "2")
                        putExtra("hour", times[0])
                        putExtra("minute", times[1])
                    }))
                }
                "SETIAP KAMIS" -> {
                    val cal = checkDayOfWeek(calendar, Calendar.THURSDAY)
                    setRepeatWeekly(cal.timeInMillis, getPendingIntent(getIntent().apply {
                        putExtra("title",item.title)
                        putExtra("message", item.message)
                        putExtra("alarmId", item.id.toString())
                        putExtra("r", "2")
                        putExtra("hour", times[0])
                        putExtra("minute", times[1])
                    }))
                }
                "SETIAP JUMAT" -> {
                    val cal = checkDayOfWeek(calendar, Calendar.FRIDAY)
                    setRepeatWeekly(cal.timeInMillis, getPendingIntent(getIntent().apply {
                        putExtra("title",item.title)
                        putExtra("message", item.message)
                        putExtra("alarmId", item.id.toString())
                        putExtra("r", "2")
                        putExtra("hour", times[0])
                        putExtra("minute", times[1])
                    }))
                }
                "SETIAP SABTU" -> {
                    val cal = checkDayOfWeek(calendar, Calendar.SATURDAY)
                    setRepeatWeekly(cal.timeInMillis, getPendingIntent(getIntent().apply {
                        putExtra("title",item.title)
                        putExtra("message", item.message)
                        putExtra("alarmId", item.id.toString())
                        putExtra("r", "2")
                        putExtra("hour", times[0])
                        putExtra("minute", times[1])
                    }))
                }
                "SETIAP MINGGU" -> {
                    val cal = checkDayOfWeek(calendar, Calendar.SUNDAY)
                    setRepeatWeekly(cal.timeInMillis, getPendingIntent(getIntent().apply {
                        putExtra("title",item.title)
                        putExtra("message", item.message)
                        putExtra("alarmId", item.id.toString())
                        putExtra("r", "2")
                        putExtra("hour", times[0])
                        putExtra("minute", times[1])
                    }))
                }
            }

        } else { //kalau alarm tidak diulang
            var date = item.date.split("-")

            var today = calendar.get(Calendar.DAY_OF_MONTH)
            Log.d("alarm", "today : $today")

            calendar.set(Calendar.YEAR, Integer.parseInt(date[0]))
            calendar.set(Calendar.MONTH, Integer.parseInt(date[1])-1)
            calendar.set(Calendar.DATE, Integer.parseInt(date[2]))

            today = calendar.get(Calendar.DAY_OF_MONTH)
            Log.d("alarm", "today 2 : $today")

            Log.d("alarm", "today is : ${Calendar.getInstance().get(Calendar.DAY_OF_MONTH)}-${Calendar.getInstance().get(Calendar.MONTH)}-${Calendar.getInstance().get(Calendar.YEAR)} ${Calendar.getInstance().get(Calendar.HOUR_OF_DAY)}:${Calendar.getInstance().get(Calendar.MINUTE)}:${Calendar.getInstance().get(Calendar.SECOND)}")
            Log.d("alarm", "alarm set on : ${calendar.get(Calendar.DAY_OF_MONTH)}-${calendar.get(Calendar.MONTH)}-${calendar.get(Calendar.YEAR)} ${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}:${calendar.get(Calendar.SECOND)}")

            setAlarm(timeInMillis, getPendingIntent(getIntent().apply {
                putExtra("title",item.title)
                putExtra("message", item.message)
                putExtra("alarmId", item.id.toString())
                putExtra("r", "0")
                putExtra("hour", times[0])
                putExtra("minute", times[1])
            }))

//            Toast.makeText(context, "berhasil buat alarm ${item.message} saat ${item.time}", Toast.LENGTH_SHORT)
        }


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            alarmManager.setExactAndAllowWhileIdle(
//                AlarmManager.RTC_WAKEUP,
//                item.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
//                PendingIntent.getBroadcast(
//                    context,
//                    item.hashCode(),
//                    intent,
//                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//                )
//            )
//        }
    }

    override fun cancel(item: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                itemId,
                Intent(context, AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    fun setAlarm(timeInMillis: Long, pendingIntent: PendingIntent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
//                    Calendar.getInstance().timeInMillis + 5000,
                timeInMillis,
                pendingIntent
            )
        }else{
//                alarmManager.setExact(
//                    AlarmManager.RTC_WAKEUP,
//                    timeInMillis,
//                    pendingIntent
//                )

            // CHECK WITH SETEXACT !!!!
            alarmManager.set(
                AlarmManager.RTC_WAKEUP,
                timeInMillis,
                pendingIntent
            )
        }
    }


    private fun checkDayOfWeek(calendar: Calendar, dayOfWeek: Int) : Calendar{
        if(calendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
            if (calendar.time.compareTo(Date()) < 0) {
                //di hari yang sama tapi jamnya sudah lewat
                calendar.add(Calendar.WEEK_OF_MONTH, 1)
            } else {
                //di hari yang sama tapi jamnya belum lewat

            }
            Log.d("alarm", "alarm is set repeating every ${calendar.get(Calendar.DAY_OF_WEEK)}")
        } else {
            //di hari yang beda
            while(calendar.get(Calendar.DAY_OF_WEEK) != dayOfWeek) {
                calendar.add(Calendar.DAY_OF_MONTH, 1)
            }

            Log.d("alarm", "alarm is set repeating every ${calendar.get(Calendar.DAY_OF_WEEK)}")
        }
        return calendar
    }
    fun setRepeatWeekly(timeInMillis: Long, pendingIntent: PendingIntent) {
//        if(calendar.get(Calendar.DAY_OF_WEEK) == dayOfWeek) {
//            if (calendar.time.compareTo(Date()) < 0) {
//                //di hari yang sama tapi jamnya sudah lewat
//                calendar.add(Calendar.WEEK_OF_MONTH, 1)
//            } else {
//                //di hari yang sama tapi jamnya belum lewat
//
//            }
//            Log.d("alarm", "alarm is set repeating every ${calendar.get(Calendar.DAY_OF_WEEK)}")
//        } else {
//            //di hari yang beda
//            while(calendar.get(Calendar.DAY_OF_WEEK) != dayOfWeek) {
//                calendar.add(Calendar.DAY_OF_MONTH, 1)
//            }
//
//            Log.d("alarm", "alarm is set repeating every ${calendar.get(Calendar.DAY_OF_WEEK)}")
//        }

        setAlarm(timeInMillis, pendingIntent)

//        alarmManager.setRepeating(
//            AlarmManager.RTC_WAKEUP,
//            calendar.timeInMillis,
//            AlarmManager.INTERVAL_DAY*7,
//            pendingIntent
//        )
    }

    fun setRepeatDaily(timeInMillis: Long, pendingIntent: PendingIntent) {

        setAlarm(timeInMillis, pendingIntent)
    }

    fun getIntent() = Intent(context, AlarmReceiver::class.java)

    fun getPendingIntent(intent: Intent) : PendingIntent {
        val pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.getBroadcast(context, itemId, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)
        } else{
            PendingIntent.getBroadcast(context, itemId, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        return pendingIntent
    }
}