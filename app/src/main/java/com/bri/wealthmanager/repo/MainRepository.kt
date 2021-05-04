package com.bri.wealthmanager.repo

import com.bri.wealthmanager.common.convertToDisplayAmount
import com.bri.wealthmanager.entity.AssetEntity
import com.bri.wealthmanager.entity.convertToEntity

interface MainRepository {
    suspend fun getAll(): ArrayList<*>
}

class MainRepositoryImpl(private val dataSource: MainDataSource) : MainRepository {
    override suspend fun getAll(): ArrayList<*> {
        val data = dataSource.getAll()
        val totalAmount = data.sumByDouble { asset -> asset.amount }
        val result =  ArrayList<Any>(data.map { it.convertToEntity(totalAmount) })
        result.add(0, totalAmount.convertToDisplayAmount())
        return result
    }
}