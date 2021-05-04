package com.bri.wealthmanager.db.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type")
data class TypeData(
        @ColumnInfo(name = "color") val color: String,
        @ColumnInfo(name = "name") val name: String,
        @PrimaryKey(autoGenerate = true) val id: Int = 0
)
