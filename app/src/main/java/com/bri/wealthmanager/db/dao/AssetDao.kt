package com.bri.wealthmanager.db.dao

import androidx.room.*
import com.bri.wealthmanager.db.WealthDatabase
import com.bri.wealthmanager.db.data.AssetData

@Dao
interface AssetDao {
    @Query("SELECT * FROM ${WealthDatabase.DATABASE_NAME} ORDER BY id DESC")
    suspend fun getAll(): List<AssetData>

    @Query("SELECT * FROM ${WealthDatabase.DATABASE_NAME} WHERE id == :id")
    suspend fun get(id : Int) : AssetData?

    @Insert
    suspend fun insert(asset: AssetData)

    @Update
    suspend fun update(asset: AssetData)

    @Delete
    suspend fun delete(asset: AssetData)

    @Delete
    suspend fun deleteAll(vararg asset: AssetData)
}