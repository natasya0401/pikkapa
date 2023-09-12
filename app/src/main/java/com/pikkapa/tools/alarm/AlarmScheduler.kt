package com.pikkapa.tools.alarm

//import com.plcoding.alarmmanagerguide.AlarmItem

interface AlarmScheduler {
    fun schedule(item: AlarmItem, repeat:Boolean)
    fun cancel(item: AlarmItem)
}