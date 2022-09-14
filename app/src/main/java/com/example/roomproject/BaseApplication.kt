package com.example.roomproject

import android.app.Application
import android.content.Context
import androidx.room.Room

import android.content.SharedPreferences

class BaseApplication: Application() {

    companion object {
        private var INSTANCE_DB: AppDatabase? = null
        private var pref: SharedPreferences? = null

        fun getDb(applicationContext: Context): AppDatabase{
            if (INSTANCE_DB == null) {
                INSTANCE_DB = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "database-tasks"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE_DB!!
        }

        fun showFirstRunToast(applicationContext: Context): Boolean {
            if (pref == null) {
                pref = applicationContext.getSharedPreferences("pref", MODE_PRIVATE)
            }
            return pref!!.getBoolean("first_launch_key", true)
        }

        fun setFirstRunToast(applicationContext: Context, isFirstRun: Boolean) {
            if (pref == null) {
                pref = applicationContext.getSharedPreferences("pref", MODE_PRIVATE)
            }
            pref!!.edit().putBoolean("first_launch_key", isFirstRun).apply()
        }
    }

}