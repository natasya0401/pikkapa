package com.pikkapa.tools.alarm

import java.time.LocalDateTime

data class AlarmItem(
    val id: Int,
    val time: String,
    val date: String,
    val repeat: String,
    val title: String,
    val message: String
)
