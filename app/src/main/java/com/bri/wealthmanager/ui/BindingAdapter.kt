package com.bri.wealthmanager.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bri.wealthmanager.common.AppAdapter
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData

@BindingAdapter("app:data")
fun RecyclerView.setData(data: ArrayList<*>) {
    (adapter as? AppAdapter)?.set(data)
}

@BindingAdapter("lottie_autoPlay")
fun LottieAnimationView.setAutoPlay(play: Boolean) {
    if (play) playAnimation()
    else pauseAnimation()
}

@BindingAdapter("app:chart_data")
fun PieChart.setChartData(data: PieData) {
    description = null
    legend.isEnabled = false
    holeRadius = 75F
    setData(data)
    invalidate()
}