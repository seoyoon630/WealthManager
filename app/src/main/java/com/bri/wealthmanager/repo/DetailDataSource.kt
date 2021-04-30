package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.data.AssetData

interface DetailDataSource {
    suspend fun insert(data: AssetData)
    suspend fun get(id: Int): AssetData?
    suspend fun update(data : AssetData)
}

class DetailDataSourceImpl(private val database: WealthDatabase) : DetailDataSource {
    override suspend fun insert(data: AssetData) {
        database.assetDao().insert(data)
    }

    override suspend fun get(id: Int): AssetData? {
        return database.assetDao().get(id)
    }

    override suspend fun update(data : AssetData) {
        return database.assetDao().update(data)
    }

}