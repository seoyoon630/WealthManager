package com.bri.wealthmanager.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bri.wealthmanager.R
import com.bri.wealthmanager.common.AppAdapter
import com.bri.wealthmanager.common.AppHolder
import com.bri.wealthmanager.databinding.AssetItemBinding
import com.bri.wealthmanager.db.entity.AssetEntity

class AssetAdapter : AppAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppHolder {
        val inflater = LayoutInflater.from(parent.context)
        return AssetHolder(DataBindingUtil.inflate(inflater, R.layout.asset_item, parent, false))
    }

    inner class AssetHolder(override val binding: AssetItemBinding) : AppHolder(binding) {
        override fun bind(item: Any) {
            super.bind(item)
            binding.data = item as? AssetEntity
        }
    }
}
