package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.data.AssetData
import com.bri.wealthmanager.db.data.AssetWithCategoryData

interface MainDataSource {
    suspend fun getAll(): ArrayList<AssetData>
    suspend fun getAllWithCategory(): ArrayList<AssetWithCategoryData>
}

class MainDataSourceImpl(private val database: WealthDatabase) : MainDataSource {
    override suspend fun getAll(): ArrayList<AssetData> = ArrayList(database.assetDao().getAll())
    override suspend fun getAllWithCategory(): ArrayList<AssetWithCategoryData> {
        return ArrayList(database.assetDao().getAllWithCategory())
    }
}