package com.bri.wealthmanager.repo

import com.bri.wealthmanager.common.convertToDisplayAmount
import com.bri.wealthmanager.db.entity.map

interface MainRepository {
    suspend fun getAll(): ArrayList<*>
}

class MainRepositoryImpl(private val dataSource: MainDataSource) : MainRepository {
    override suspend fun getAll(): ArrayList<*> {
        val data = dataSource.getAll()
        val totalAmount = data.sumByDouble { asset -> asset.amount }
        val result = ArrayList<Any>(data.map { it.map(totalAmount) })
        result.add(0, totalAmount.convertToDisplayAmount())
        return result
    }
}