package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.entity.AssetEntity

interface MainDataSource {
    suspend fun getAll(): ArrayList<AssetEntity>
}

class MainDataSourceImpl(private val database: WealthDatabase) : MainDataSource {
    override suspend fun getAll(): ArrayList<AssetEntity> = ArrayList(database.assetDao().getAll())
}