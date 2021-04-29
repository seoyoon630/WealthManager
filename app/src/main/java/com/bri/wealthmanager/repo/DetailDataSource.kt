package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.entity.AssetEntity

interface DetailDataSource {
    suspend fun insert(data: AssetEntity)
    suspend fun get(id: Int): AssetEntity?
}

class DetailDataSourceImpl(private val database: WealthDatabase) : DetailDataSource {
    override suspend fun insert(data: AssetEntity) {
        database.assetDao().insert(data)
    }

    override suspend fun get(id: Int): AssetEntity? {
        return database.assetDao().get(id)
    }

}