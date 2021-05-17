package com.bri.wealthmanager.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bri.wealthmanager.R
import com.bri.wealthmanager.common.AppAdapter
import com.bri.wealthmanager.common.AppHolder
import com.bri.wealthmanager.databinding.ColorItemBinding

class ColorAdapter(private val selectColor: (color: String) -> Unit) : AppAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ColorHolder(DataBindingUtil.inflate(inflater, R.layout.color_item, parent, false))
    }

    inner class ColorHolder(override val binding: ColorItemBinding) : AppHolder(binding) {
        override fun bind(item: Any) {
            (item as? String)?.let { color ->
                binding.color = color
                binding.root.setOnClickListener { selectColor(color) }
            }
        }
    }
}