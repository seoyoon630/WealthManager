package com.bri.wealthmanager.db.data

import androidx.room.Embedded
import androidx.room.Relation

data class AssetWithTypeData(
    @Embedded val type: TypeData,
    @Relation(
        parentColumn = "id",
        entityColumn = "typeId"
    )
    val assets: List<AssetData>
)
