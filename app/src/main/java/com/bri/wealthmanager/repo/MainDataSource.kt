package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.orhanobut.logger.Logger

interface MainDataSource {
}

class MainDataSourceImpl(private val database : WealthDatabase) : MainDataSource {
    init {
        Logger.w("init MainDataSourceImpl $database")
    }
}