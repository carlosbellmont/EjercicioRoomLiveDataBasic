package com.cbellmont.ejercicioandroid15

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.*


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Db.getDatabase(this)
    }

}
