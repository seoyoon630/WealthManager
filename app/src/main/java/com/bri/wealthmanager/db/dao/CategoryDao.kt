package com.bri.wealthmanager.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bri.wealthmanager.db.data.CategoryData

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    suspend fun getCategories() : List<CategoryData>

    @Insert
    suspend fun insert(category : CategoryData)
}