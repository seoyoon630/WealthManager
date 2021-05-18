package com.bri.wealthmanager.db.data

import androidx.room.Embedded
import androidx.room.Relation

data class AssetAndCategoryData(
        @Embedded val asset: AssetData,
        @Relation(
                parentColumn = "categoryId",
                entityColumn = "id"
        )
        val category: CategoryData
)
