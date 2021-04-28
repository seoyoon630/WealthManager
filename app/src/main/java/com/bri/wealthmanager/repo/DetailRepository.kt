package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.entity.AssetEntity

interface DetailRepository {
    suspend fun insert(title: String, amount: Double)
}

class DetailRepositoryImpl(private val dataSource: DetailDataSource) : DetailRepository {
    override suspend fun insert(title: String, amount: Double) {
        dataSource.insert(AssetEntity(title, amount))
    }
}