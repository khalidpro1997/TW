package com.danishkhalid.TW

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class Avengers:Application() {
    companion object {
        lateinit var preferences: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        preferences = applicationContext.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

    }
}