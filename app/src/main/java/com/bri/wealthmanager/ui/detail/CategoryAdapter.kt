package com.bri.wealthmanager.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bri.wealthmanager.R
import com.bri.wealthmanager.common.AppAdapter
import com.bri.wealthmanager.common.AppHolder
import com.bri.wealthmanager.data.Category
import com.bri.wealthmanager.databinding.CategoryAddItemBinding
import com.bri.wealthmanager.databinding.CategoryItemBinding

class CategoryAdapter(
        private val addCategory: () -> Unit,
        private val selectCategory: (category: Category) -> Unit) : AppAdapter() {

    override fun set(pList: ArrayList<*>) {
        val list = arrayListOf<Any?>().apply {
            addAll(pList)
            add(null)
        }
        super.set(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> CategoryAddHolder(DataBindingUtil.inflate(inflater, R.layout.category_add_item, parent, false))
            1 -> CategoryHolder(DataBindingUtil.inflate(inflater, R.layout.category_item, parent, false))
            else -> throw Exception("AssetAdapter : Wrong viewType $viewType")
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when {
            mList[position] == null -> 0
            mList[position] is Category -> 1
            else -> throw Exception("CategoryAdapter : Wrong Data ${mList[position]}")
        }
    }

    inner class CategoryAddHolder(override val binding: CategoryAddItemBinding) : AppHolder(binding) {
        override fun bind() {
            super.bind()
            binding.root.setOnClickListener { addCategory() }
        }
    }

    inner class CategoryHolder(override val binding: CategoryItemBinding) : AppHolder(binding) {
        override fun bind(item: Any) {
            (item as? Category)?.let {
                binding.root.setOnClickListener { _ -> selectCategory(it) }
                binding.data = it
            }
        }
    }
}