package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.data.AssetData

interface MainDataSource {
    suspend fun getAll(): ArrayList<AssetData>
}

class MainDataSourceImpl(private val database: WealthDatabase) : MainDataSource {
    override suspend fun getAll(): ArrayList<AssetData> = ArrayList(database.assetDao().getAll())
}