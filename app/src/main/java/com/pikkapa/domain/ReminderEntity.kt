package com.pikkapa.domain

data class ReminderEntity (
    var id: Int = 0,
    var title: String = "judul",
    var time: String = "00:00:00", //hh:mm:ss
    var date: String = "", //dd-mm-yyyy
    var repeatDaily: Boolean = false,
    var repeatWeekly: Boolean = false,
    var repeatWeeklyDay: String = "", //day of the week 0-6
    var repeatMonthly: Boolean = false,
    var repeatMothlyDate: String = "", //date of the month 1-31
    var repeatCustom: Boolean = false,
    var repeatCustomDays: ArrayList<String> = ArrayList(), //days for alarm to turn on
    var alarmIds: ArrayList<String> = ArrayList(), //alarm id
        )