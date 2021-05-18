package com.bri.wealthmanager.entity

import com.bri.wealthmanager.db.data.AssetAndCategoryData
import com.bri.wealthmanager.db.data.AssetData

data class AssetAndCategoryEntity(val asset : AssetData, val category : CategoryEntity)

fun AssetAndCategoryData.convertToEntity() : AssetAndCategoryEntity {
    return AssetAndCategoryEntity(asset, category.convertToEntity())
}