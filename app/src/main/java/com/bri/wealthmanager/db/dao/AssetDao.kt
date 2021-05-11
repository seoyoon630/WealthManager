package com.bri.wealthmanager.db.dao

import androidx.room.*
import com.bri.wealthmanager.db.data.AssetData
import com.bri.wealthmanager.db.data.AssetWithTypeData

@Dao
interface AssetDao {
    @Query("SELECT * FROM asset ORDER BY id DESC")
    suspend fun getAll(): List<AssetData>

    @Transaction
    @Query("SELECT * FROM type")
    suspend fun getAllWithType(): List<AssetWithTypeData>

    @Query("SELECT * FROM asset WHERE id == :id")
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