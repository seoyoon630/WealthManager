package com.bri.wealthmanager.entity

import android.graphics.Color
import com.bri.wealthmanager.db.data.CategoryData

data class CategoryEntity(
        val id: Int,
        val color: Int,
        val name: String
)

fun CategoryData.convertToEntity(): CategoryEntity {
    return CategoryEntity(id, Color.parseColor(color), name)
}