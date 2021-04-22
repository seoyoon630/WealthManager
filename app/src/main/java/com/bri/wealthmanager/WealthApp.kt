package com.bri.wealthmanager

import android.app.Application
import com.bri.wealthmanager.db.WealthDatabase

class WealthApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    fun getDatabase(): WealthDatabase {
        return WealthDatabase.getInstance(this)
    }

//    fun getRepository(): DataRepository? {
//        return DataRepository.getInstance(getDatabase())
//    }
}