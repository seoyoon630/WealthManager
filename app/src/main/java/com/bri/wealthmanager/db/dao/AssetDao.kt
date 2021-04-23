package com.bri.wealthmanager.db.dao

import androidx.room.*
import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.entity.AssetEntity

@Dao
interface AssetDao {
    @Query("SELECT * FROM ${WealthDatabase.DATABASE_NAME}")
    fun getAll(): List<AssetEntity>

    @Insert
    fun insert(asset: AssetEntity)

    @Update
    fun update(asset: AssetEntity)

    @Delete
    fun delete(asset: AssetEntity)

    @Delete
    fun deleteAll(vararg asset: AssetEntity)
}