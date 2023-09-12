package com.pikkapa.entity

data class ReminderEntity (
    var id: Int = 0,
    var title: String = "judul",
    var notes: String = "",
    var time: String = "00:00:00", //hh:mm:ss
    var date: String = "", //dd-mm-yyyy
    var repeatDaily: Boolean = false,
    var repeatWeekly: Boolean = false,
    var repeatWeeklyDay: String = "", //day of the week 0-6
    var repeatCustom: Boolean = false,
    var repeatCustomDays: List<String> = ArrayList(), //days for alarm to turn on
    var alarmIds: List<String> = ArrayList(), //alarm id
        )