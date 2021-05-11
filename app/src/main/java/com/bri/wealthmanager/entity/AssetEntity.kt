package com.bri.wealthmanager.entity

import android.graphics.Color
import com.bri.wealthmanager.db.data.AssetData
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

data class AssetEntity(
    val name: String,
    val amount: Double,
    val displayAmount: String,
    val id: Int? = null,
    val ratio: String,
    val pieData: PieData
)

fun AssetData.convertToEntity(totalAmount: Double): AssetEntity {
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
    return AssetEntity(name, amount, displayAmount, id, "${ratio.toInt()}%", data)
}