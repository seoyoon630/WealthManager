package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.entity.AssetEntity
import com.orhanobut.logger.Logger

interface MainDataSource {
    suspend fun getAll(): ArrayList<AssetEntity>
}

class MainDataSourceImpl(private val database: WealthDatabase) : MainDataSource {
    init {
        Logger.w("init MainDataSourceImpl $database")
    }

    override suspend fun getAll(): ArrayList<AssetEntity> = ArrayList(database.assetDao().getAll())

}