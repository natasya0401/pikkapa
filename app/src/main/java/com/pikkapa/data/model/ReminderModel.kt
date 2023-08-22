package com.pikkapa.data.model

data class ReminderModel (
    var title:String = "",
    var time:String = "", //HH:MM:SS
    var description:String = "",
    var repeat:Boolean = false,
    var repeat_days:Int = 0,
    var repeat_monday:Boolean = false,


)