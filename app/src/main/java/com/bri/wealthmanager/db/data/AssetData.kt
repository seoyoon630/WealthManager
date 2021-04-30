package com.bri.wealthmanager.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bri.wealthmanager.db.WealthDatabase

@Entity(tableName = WealthDatabase.DATABASE_NAME)
data class AssetData(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "amount") val amount: Double,
    @ColumnInfo(name = "displayAmount") val displayAmount : String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)