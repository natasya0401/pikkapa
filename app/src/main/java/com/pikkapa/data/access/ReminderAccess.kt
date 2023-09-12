package com.pikkapa.data.access

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteStatement
import com.pikkapa.data.DBBase
import com.pikkapa.entity.ReminderEntity
import kotlin.collections.ArrayList

open class ReminderAccess(context: Context) : DBBase(context) {

    companion object {
        private const val table = "t_reminder"
        const val createQuery = "CREATE TABLE $table" +
                " (`id` INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " `title` VARCHAR(100) NOT NULL, " +
                " `notes` TEXT NOT NULL, " +
                " `time` VARCHAR(100) NOT NULL, " +
                " `date` VARCHAR(100) NOT NULL, " +
                " `repeat_daily` INTEGER NOT NULL, " +
                " `repeat_weekly` INTEGER NOT NULL, " +
                " `repeat_weekly_day` VARCHAR(100) NOT NULL, " +
                " `repeat_custom` INTEGER NOT NULL, " +
                " `repeat_custom_days` VARCHAR(100) NOT NULL, " +
                " `alarm_ids` VARCHAR(100) NOT NULL " +
                " ) "

        const val dropQuery = "DROP TABLE IF EXISTS $table"
        private const val insertOrReplaceQuery = "INSERT OR REPLACE INTO $table (" +
                " 'title', 'notes', 'time', 'date', 'repeat_daily', " +
                " 'repeat_weekly', 'repeat_weekly_day', 'repeat_custom', 'repeat_custom_days', 'alarm_ids')  " +
                " VALUES ( ?, ?, ?, ?, ?, " +
                " ?, ?, ?, ?, ?) "
    }

    fun getAll():ArrayList<ReminderEntity> {
        var rtn: ArrayList<ReminderEntity> = ArrayList()
        open()

        val query = "SELECT * FROM $table"
        val cursor: Cursor = database.rawQuery(query, arrayOf<String>())
        cursor.moveToFirst()
        while (cursor.moveToNext()) {
//            try {
                var i = 0
                val reminderEntity = ReminderEntity()

                reminderEntity.id = cursor.getInt(if(cursor.getColumnIndex("id")==-1) 0 else {
                    cursor.getColumnIndex("id")
                }
                )
                reminderEntity.title = cursor.getString(if(cursor.getColumnIndex("title")==-1) 0 else {
                    cursor.getColumnIndex("title")
                }
                )
                reminderEntity.notes = cursor.getString(if(cursor.getColumnIndex("notes")==-1) 0 else {
                    cursor.getColumnIndex("notes")
                }
                )
                reminderEntity.time = cursor.getString(if(cursor.getColumnIndex("time")==-1) 0 else {
                    cursor.getColumnIndex("time")
                }
                )
                reminderEntity.date = cursor.getString(if(cursor.getColumnIndex("date")==-1) 0 else {
                    cursor.getColumnIndex("date")
                }
                )
                reminderEntity.repeatDaily = cursor.getInt(if(cursor.getColumnIndex("repeat_daily")==-1) 0 else {
                    cursor.getColumnIndex("repeat_daily")
                }
                ) == 1
                reminderEntity.repeatWeekly = 1 == cursor.getInt(if(cursor.getColumnIndex("repeat_weekly")==-1) 0 else {
                    cursor.getColumnIndex("repeat_weekly")
                }
                )
                reminderEntity.repeatWeeklyDay = cursor.getString(if(cursor.getColumnIndex("repeat_weekly_day")==-1) 0 else {
                    cursor.getColumnIndex("repeat_weekly_day")
                }
                )
                reminderEntity.repeatCustom = cursor.getInt(if(cursor.getColumnIndex("repeat_custom")==-1) 0 else {
                    cursor.getColumnIndex("repeat_custom")
                }
                ) == 1
                reminderEntity.repeatCustomDays = cursor.getString(if(cursor.getColumnIndex("repeat_custom_days")==-1) 0 else {
                    cursor.getColumnIndex("repeat_custom_days")
                }
                ).split(",")
                reminderEntity.alarmIds = cursor.getString(if(cursor.getColumnIndex("alarm_ids")==-1) 0 else {
                    cursor.getColumnIndex("alarm_ids")
                }
                ).split(",")

                rtn.add(reminderEntity)
//            } catch (e: java.lang.Exception) {
//
//            }
        }

        cursor.close()
        close()

        return rtn

    }

    fun insert(reminderEntity: ReminderEntity):Boolean {
        var rtn = false
        open()

        database.beginTransactionNonExclusive()
        val statement: SQLiteStatement = database.compileStatement(insertOrReplaceQuery)
        var i = 1

        statement.bindString(i++, reminderEntity.title)
        statement.bindString(i++, reminderEntity.notes)
        statement.bindString(i++, reminderEntity.time)
        statement.bindString(i++, reminderEntity.date)
        statement.bindLong(i++, if(reminderEntity.repeatDaily) 1 else 0)
        statement.bindLong(i++, if(reminderEntity.repeatWeekly) 1 else 0)
        statement.bindString(i++, reminderEntity.repeatWeeklyDay)
        statement.bindLong(i++, if(reminderEntity.repeatCustom) 1 else 0)
        statement.bindString(i++, reminderEntity.repeatCustomDays.joinToString(",").replace("[", "").replace("]", ""))
        statement.bindString(i++, reminderEntity.alarmIds.joinToString(",").replace("[", "").replace("]", ""))

        var id = statement.executeInsert()
        statement.clearBindings()
        rtn = true
        database.setTransactionSuccessful()

        database.endTransaction()
        close()

        return id>-1
    }

    fun delete(alarmId:Int): Int {
        var result = 0
        open()
        result = database.delete(table, "id=?", arrayOf("$alarmId"))
        close()
        return result
    }


    fun getLatestId():Int {
        var rtn: Int = 0
        open()

        val query = "SELECT id FROM $table ORDER BY id DESC LIMIT 1 "
        val cursor: Cursor = database.rawQuery(query, arrayOf<String>())
        while (cursor.moveToNext()) {
            try {
                var i = 0

                rtn = cursor.getInt(if(cursor.getColumnIndex("id")==-1) 0 else {
                    cursor.getColumnIndex("id")
                })

            } catch (e: java.lang.Exception) {

            }
        }

        cursor.close()
        close()

        return rtn

    }

}