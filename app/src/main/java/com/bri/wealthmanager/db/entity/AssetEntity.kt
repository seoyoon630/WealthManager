package com.bri.wealthmanager.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bri.wealthmanager.db.WealthDatabase

@Entity(tableName = WealthDatabase.DATABASE_NAME)
class AssetEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "amount") val amount: Double
)