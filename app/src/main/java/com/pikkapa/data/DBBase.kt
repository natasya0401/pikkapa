package com.pikkapa.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log

open class DBBase(context: Context) {

    private var helper : DBHelper = DBHelper(context)
    protected lateinit var database: SQLiteDatabase

    fun open(){
        database = helper.readableDatabase
    }

    fun close() {
        helper.close()
        database.close()
    }

}