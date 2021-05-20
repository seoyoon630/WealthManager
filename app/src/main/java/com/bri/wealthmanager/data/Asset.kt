package com.bri.wealthmanager.data

import com.github.mikephil.charting.data.PieData

data class Asset(
        val id: Int,
        val name: String,
        val amount: Double,
        val displayAmount: String,
        val category : Category,
        val ratio: String,
        val pieData: PieData
)