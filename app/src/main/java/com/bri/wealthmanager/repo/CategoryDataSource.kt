package com.bri.wealthmanager.repo

import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.data.CategoryData

interface CategoryDataSource {
    suspend fun getCategories() : List<CategoryData>
}

class CategoryDataSourceImpl(private val database: WealthDatabase) : CategoryDataSource {
    override suspend fun getCategories(): List<CategoryData> {
        return database.categoryDao().getCategories()
    }
}