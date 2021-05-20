package com.bri.wealthmanager.db.dao

import androidx.room.*
import com.bri.wealthmanager.db.entity.AssetEntity

@Dao
interface AssetDao {
    @Query("SELECT * FROM asset ORDER BY id DESC")
    suspend fun getAll(): List<AssetEntity>

    @Query("SELECT * FROM asset WHERE id == :id")
    suspend fun get(id: Int): AssetEntity?

    @Insert
    suspend fun insert(asset: AssetEntity)

    @Update
    suspend fun update(asset: AssetEntity)

    @Delete
    suspend fun delete(asset: AssetEntity)

    @Delete
    suspend fun deleteAll(vararg asset: AssetEntity)
}