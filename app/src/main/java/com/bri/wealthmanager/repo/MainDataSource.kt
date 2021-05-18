package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.data.AssetData
import com.bri.wealthmanager.db.data.CategoryWithAssetsData

interface MainDataSource {
    suspend fun getAll(): ArrayList<AssetData>
    suspend fun getAllWithCategory(): ArrayList<CategoryWithAssetsData>
}

class MainDataSourceImpl(private val database: WealthDatabase) : MainDataSource {
    override suspend fun getAll(): ArrayList<AssetData> = ArrayList(database.assetDao().getAll())
    override suspend fun getAllWithCategory(): ArrayList<CategoryWithAssetsData> {
        return ArrayList(database.assetDao().getAllWithCategory())
    }
}