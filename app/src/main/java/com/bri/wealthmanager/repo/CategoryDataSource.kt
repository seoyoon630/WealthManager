package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.data.CategoryData

interface CategoryDataSource {
    suspend fun getCategories() : List<CategoryData>
    suspend fun insert(categoryData: CategoryData)

}

class CategoryDataSourceImpl(private val database: WealthDatabase) : CategoryDataSource {
    override suspend fun getCategories(): List<CategoryData> {
        return database.categoryDao().getCategories()
    }

    override suspend fun insert(categoryData: CategoryData) {
        return database.categoryDao().insert(categoryData)
    }
}