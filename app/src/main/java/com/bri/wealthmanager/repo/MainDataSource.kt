package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.data.AssetData
import com.bri.wealthmanager.db.data.AssetWithTypeData

interface MainDataSource {
    suspend fun getAll(): ArrayList<AssetData>
    suspend fun getAllWithType(): ArrayList<AssetWithTypeData>
}

class MainDataSourceImpl(private val database: WealthDatabase) : MainDataSource {
    override suspend fun getAll(): ArrayList<AssetData> = ArrayList(database.assetDao().getAll())
    override suspend fun getAllWithType(): ArrayList<AssetWithTypeData> {
        return ArrayList(database.assetDao().getAllWithType())
    }
}