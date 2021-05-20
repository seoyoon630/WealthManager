package com.bri.wealthmanager.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bri.wealthmanager.db.entity.CategoryEntity

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    suspend fun getCategories(): List<CategoryEntity>

    @Insert
    suspend fun insert(category: CategoryEntity)
}