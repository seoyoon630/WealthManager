package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.entity.CategoryEntity

interface CategoryDataSource {
    suspend fun getCategories(): List<CategoryEntity>
    suspend fun insert(entity: CategoryEntity)
}

class CategoryDataSourceImpl(private val database: WealthDatabase) : CategoryDataSource {
    override suspend fun getCategories(): List<CategoryEntity> {
        return database.categoryDao().getCategories()
    }

    override suspend fun insert(entity: CategoryEntity) {
        return database.categoryDao().insert(entity)
    }
}