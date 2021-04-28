package com.bri.wealthmanager.ui

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bri.wealthmanager.common.AppAdapter

@BindingAdapter("app:data")
fun RecyclerView.setData(data: ArrayList<*>) {
    (adapter as? AppAdapter)?.set(data)
}

@BindingAdapter("app:setText")
fun TextView.setText(text: Any?) {
    this.text = text?.toString() ?: "null"
}
