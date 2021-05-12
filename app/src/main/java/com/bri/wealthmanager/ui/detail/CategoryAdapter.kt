package com.bri.wealthmanager.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bri.wealthmanager.R
import com.bri.wealthmanager.common.AppAdapter
import com.bri.wealthmanager.common.AppHolder
import com.bri.wealthmanager.databinding.CategoryItemBinding
import com.bri.wealthmanager.entity.CategoryEntity

class CategoryAdapter(private val selectCategory: (category: CategoryEntity) -> Unit) : AppAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryHolder(DataBindingUtil.inflate(inflater, R.layout.category_item, parent, false))
    }

    inner class CategoryHolder(override val binding: CategoryItemBinding) : AppHolder(binding) {
        override fun bind(item: Any) {
            (item as? CategoryEntity)?.let {
                binding.root.setOnClickListener { _ -> selectCategory(it) }
                binding.data = it
            }
        }
    }
}