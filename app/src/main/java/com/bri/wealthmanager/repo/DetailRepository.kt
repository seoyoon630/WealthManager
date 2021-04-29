package com.bri.wealthmanager.repo

import com.bri.wealthmanager.common.convertToDisplayAmount
import com.bri.wealthmanager.db.entity.AssetEntity

interface DetailRepository {
    suspend fun insert(title: String, amount: Double)
    abstract suspend fun get(id: Int): AssetEntity?
}

class DetailRepositoryImpl(private val dataSource: DetailDataSource) : DetailRepository {
    override suspend fun insert(title: String, amount: Double) {
        dataSource.insert(AssetEntity(title, amount, amount.convertToDisplayAmount()))
    }

    override suspend fun get(id: Int): AssetEntity? {
        return dataSource.get(id)
    }
}