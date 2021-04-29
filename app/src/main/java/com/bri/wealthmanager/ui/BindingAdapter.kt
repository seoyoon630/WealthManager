package com.bri.wealthmanager.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bri.wealthmanager.common.AppAdapter

@BindingAdapter("app:data")
fun RecyclerView.setData(data: ArrayList<*>) {
    (adapter as? AppAdapter)?.set(data)
}

@BindingAdapter("lottie_autoPlay")
fun LottieAnimationView.setAutoPlay(play: Boolean) {
    if (play) playAnimation()
    else pauseAnimation()
}