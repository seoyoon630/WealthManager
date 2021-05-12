package com.bri.wealthmanager.repo

import com.bri.wealthmanager.entity.CategoryEntity
import com.bri.wealthmanager.entity.convertToEntity

interface CategoryRepository {
    suspend fun getCategories(): ArrayList<CategoryEntity>
}

class CategoryRepositoryImpl(private val dataSource: CategoryDataSource) : CategoryRepository {
    override suspend fun getCategories(): ArrayList<CategoryEntity> {
        return ArrayList(dataSource.getCategories().map { it.convertToEntity() })
    }
}