package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.entity.AssetEntity

interface DetailDataSource {
    suspend fun insert(entity: AssetEntity)
    suspend fun get(id: Int): AssetEntity?
    suspend fun update(entity: AssetEntity)
}

class DetailDataSourceImpl(private val database: WealthDatabase) : DetailDataSource {
    override suspend fun insert(entity: AssetEntity) {
        database.assetDao().insert(entity)
    }

    override suspend fun get(id: Int): AssetEntity? {
        return database.assetDao().get(id)
    }

    override suspend fun update(entity: AssetEntity) {
        return database.assetDao().update(entity)
    }

}