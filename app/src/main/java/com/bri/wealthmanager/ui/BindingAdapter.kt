package com.bri.wealthmanager.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bri.wealthmanager.common.AppAdapter

@BindingAdapter("app:data")
fun RecyclerView.setData(data: ArrayList<*>) {
    (adapter as? AppAdapter)?.set(data)
}
