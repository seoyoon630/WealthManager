package com.bri.wealthmanager.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bri.wealthmanager.db.WealthDatabase

@Entity(tableName = "asset")
data class AssetData(
        @ColumnInfo(name = "typeId") val typeId: Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "amount") val amount: Double,
        @ColumnInfo(name = "displayAmount") val displayAmount: String,
        @PrimaryKey(autoGenerate = true) val id: Int = 0
)