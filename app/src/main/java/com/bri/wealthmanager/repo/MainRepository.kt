package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.entity.AssetEntity

interface MainRepository {
    suspend fun getAll(): ArrayList<AssetEntity>
}

class MainRepositoryImpl(private val dataSource: MainDataSource) : MainRepository {
    override suspend fun getAll(): ArrayList<AssetEntity> = dataSource.getAll()
}