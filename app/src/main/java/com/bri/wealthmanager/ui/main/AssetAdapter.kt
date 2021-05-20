package com.bri.wealthmanager.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bri.wealthmanager.R
import com.bri.wealthmanager.common.AppAdapter
import com.bri.wealthmanager.common.AppHolder
import com.bri.wealthmanager.databinding.AssetItemBinding
import com.bri.wealthmanager.databinding.TotalItemBinding
import com.bri.wealthmanager.data.Asset

class AssetAdapter(private val showDetail: (id: Int?) -> Unit) : AppAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> TotalHolder(DataBindingUtil.inflate(inflater, R.layout.total_item, parent, false))
            1 -> AssetHolder(DataBindingUtil.inflate(inflater, R.layout.asset_item, parent, false))
            else -> throw Exception("AssetAdapter : Wrong viewType $viewType")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (mList[position]) {
            is String -> 0
            is Asset -> 1
            else -> throw Exception("AssetAdapter : Wrong Data ${mList[position]}")
        }
    }

    inner class TotalHolder(override val binding: TotalItemBinding) : AppHolder(binding) {
        override fun bind(item: Any) {
            (item as? String)?.let { binding.data = it }
        }
    }

    inner class AssetHolder(override val binding: AssetItemBinding) :
            AppHolder(binding) {
        override fun bind(item: Any) {
            (item as? Asset)?.let {
                binding.data = it
                binding.root.setOnClickListener { _ -> showDetail(it.id) }
            }
        }
    }
}
