package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.entity.AssetEntity
import com.orhanobut.logger.Logger

interface MainRepository {
    suspend fun getAll() : ArrayList<AssetEntity>
}

class MainRepositoryImpl(private val dataSource: MainDataSource) : MainRepository{
    init {
        Logger.w("init MainRepositoryImpl $dataSource")
    }

    override suspend fun getAll(): ArrayList<AssetEntity> = dataSource.getAll()
}