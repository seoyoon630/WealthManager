package com.bri.wealthmanager.repo

import com.bri.wealthmanager.common.convertToDisplayAmount
import com.bri.wealthmanager.db.data.AssetData

interface DetailRepository {
    suspend fun insert(title: String, amount: Double)
    suspend fun get(id: Int): AssetData?
    suspend fun update(id: Int, name: String, amount: Double)
}

class DetailRepositoryImpl(private val dataSource: DetailDataSource) : DetailRepository {
    override suspend fun insert(title: String, amount: Double) {
        dataSource.insert(AssetData(title, amount, amount.convertToDisplayAmount()))
    }

    override suspend fun get(id: Int): AssetData? {
        return dataSource.get(id)
    }

    override suspend fun update(id: Int, name: String, amount: Double) {
        return dataSource.update(AssetData(name, amount, amount.convertToDisplayAmount(), id))
    }
}