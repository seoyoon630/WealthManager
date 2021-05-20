package com.bri.wealthmanager.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bri.wealthmanager.data.Category

@Entity(tableName = "category")
data class CategoryEntity(
        @ColumnInfo(name = "category_name") val name: String,
        @ColumnInfo(name = "color") val color: Int,
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "category_id") val id: Int = 0
)


fun CategoryEntity.map(): Category {
    return Category(id, name, color)
}