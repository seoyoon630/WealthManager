package com.bri.wealthmanager.repo

import com.bri.wealthmanager.common.convertToDisplayAmount
import com.bri.wealthmanager.data.Asset
import com.bri.wealthmanager.data.Category
import com.bri.wealthmanager.db.entity.AssetEntity
import com.bri.wealthmanager.db.entity.CategoryEntity
import com.bri.wealthmanager.db.entity.map

interface DetailRepository {
    suspend fun insert(name: String, amount: Double, category: Category?)
    suspend fun get(id: Int): Asset?
    suspend fun update(id: Int, name: String, amount: Double, category: Category?)
}

class DetailRepositoryImpl(private val dataSource: DetailDataSource) : DetailRepository {
    override suspend fun insert(name: String, amount: Double, category: Category?) {
        if (category == null) throw Exception("Asset Insert : category can not be null.")
        val categoryEntity = CategoryEntity(category.name, category.color, category.id)
        val entity = AssetEntity(name, amount, amount.convertToDisplayAmount(), categoryEntity)
        dataSource.insert(entity)
    }

    override suspend fun get(id: Int): Asset? {
        return dataSource.get(id)?.map()
    }

    override suspend fun update(id: Int, name: String, amount: Double, category: Category?) {
        if (category == null) throw Exception("Asset Update : category can not be null.")
        val categoryEntity = CategoryEntity(category.name, category.color, category.id)
        val entity = AssetEntity(name, amount, amount.convertToDisplayAmount(), categoryEntity)
        dataSource.update(entity)
    }
}