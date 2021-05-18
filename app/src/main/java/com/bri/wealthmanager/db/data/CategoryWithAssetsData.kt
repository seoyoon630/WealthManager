package com.bri.wealthmanager.db.data

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithAssetsData(
        @Embedded val category: CategoryData,
        @Relation(
                parentColumn = "id",
                entityColumn = "categoryId"
        )
        val assets: List<AssetData>
)
