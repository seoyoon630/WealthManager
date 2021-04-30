package com.bri.wealthmanager.repo

import com.bri.wealthmanager.entity.AssetEntity
import com.bri.wealthmanager.entity.convertToEntity

interface MainRepository {
    suspend fun getAll(): ArrayList<AssetEntity>
}

class MainRepositoryImpl(private val dataSource: MainDataSource) : MainRepository {
    override suspend fun getAll(): ArrayList<AssetEntity> {
        val data = dataSource.getAll()
        val totalAmount = data.map { it.amount }.sum()
        return ArrayList(data.map { it.convertToEntity(totalAmount) })
    }
}