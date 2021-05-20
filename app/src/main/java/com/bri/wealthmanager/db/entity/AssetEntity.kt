package com.bri.wealthmanager.db.entity

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bri.wealthmanager.data.Asset
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

@Entity(tableName = "asset")
data class AssetEntity(
        val name: String,
        val amount: Double,
        @ColumnInfo(name = "display_amount") val displayAmount: String,
        @Embedded val category: CategoryEntity,
        @PrimaryKey(autoGenerate = true) val id: Int = 0,
)

fun AssetEntity.map(totalAmount: Double = amount): Asset {
    val ratio = (amount / totalAmount * 100).toFloat()
    val entries = ArrayList<PieEntry>().apply {
        add(PieEntry(ratio, ""))
        add(PieEntry(100 - ratio, ""))
    }
    val set = PieDataSet(entries, "").apply {
        colors = listOf(Color.parseColor("#1A2E40"), Color.parseColor("#EFEEEE"))
    }
    val data = PieData(set).apply {
        setDrawValues(false)
    }
    return Asset(id, name, amount, displayAmount, category.map(), "${ratio.toInt()}%", data)
}