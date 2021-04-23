package com.bri.wealthmanager.repo

import com.orhanobut.logger.Logger

interface MainDataSource {
}

class MainDataSourceImpl() : MainDataSource {
    init {
        Logger.w("init MainDataSourceImpl")
    }
}