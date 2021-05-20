package com.bri.wealthmanager.repo

import com.bri.wealthmanager.data.Category
import com.bri.wealthmanager.db.entity.CategoryEntity
import com.bri.wealthmanager.db.entity.map

interface CategoryRepository {
    suspend fun getCategories(): ArrayList<Category>
    suspend fun insert(name: String, color: Int)
}

class CategoryRepositoryImpl(private val dataSource: CategoryDataSource) : CategoryRepository {
    override suspend fun getCategories(): ArrayList<Category> {
        return ArrayList(dataSource.getCategories().map { it.map() })
    }

    override suspend fun insert(name: String, color: Int) {
        dataSource.insert(CategoryEntity(name, color))
    }
}