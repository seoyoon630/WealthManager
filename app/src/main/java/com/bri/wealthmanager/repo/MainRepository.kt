package com.bri.wealthmanager.repo

import com.orhanobut.logger.Logger

interface MainRepository {
}

class MainRepositoryImpl(private val dataSource: MainDataSource) : MainRepository{
    init {
        Logger.w("init MainRepositoryImpl $dataSource")
    }
}