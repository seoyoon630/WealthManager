package com.bri.wealthmanager.repo

import com.bri.wealthmanager.common.convertToDisplayAmount
import com.bri.wealthmanager.db.data.AssetAndCategoryData
import com.bri.wealthmanager.db.data.AssetData
import com.bri.wealthmanager.entity.AssetAndCategoryEntity
import com.bri.wealthmanager.entity.convertToEntity

interface DetailRepository {
    suspend fun insert(title: String, amount: Double, categoryId: Int?)
    suspend fun get(id: Int): AssetAndCategoryEntity?
    suspend fun update(id: Int, name: String, amount: Double, categoryId: Int?)
}

class DetailRepositoryImpl(private val dataSource: DetailDataSource) : DetailRepository {
    override suspend fun insert(title: String, amount: Double, categoryId: Int?) {
        if (categoryId == null) return
        val data = AssetData(categoryId, title, amount, amount.convertToDisplayAmount())
        dataSource.insert(data)
    }

    override suspend fun get(id: Int): AssetAndCategoryEntity? {
        return dataSource.get(id)?.convertToEntity()
    }

    override suspend fun update(id: Int, name: String, amount: Double, categoryId: Int?) {
        if (categoryId == null) return
        val data = AssetData(categoryId, name, amount, amount.convertToDisplayAmount(), id)
        dataSource.update(data)
    }
}