package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.data.CategoryData
import com.bri.wealthmanager.entity.CategoryEntity
import com.bri.wealthmanager.entity.convertToEntity

interface CategoryRepository {
    suspend fun getCategories(): ArrayList<CategoryEntity>
    suspend fun insert(name : String, color : String)
}

class CategoryRepositoryImpl(private val dataSource: CategoryDataSource) : CategoryRepository {
    override suspend fun getCategories(): ArrayList<CategoryEntity> {
        return ArrayList(dataSource.getCategories().map { it.convertToEntity() })
    }

    override suspend fun insert(name: String, color: String) {
        dataSource.insert(CategoryData(color, name))
    }
}