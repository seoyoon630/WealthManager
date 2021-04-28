package com.bri.wealthmanager.common

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class AppAdapter : RecyclerView.Adapter<AppHolder>() {

    protected val mList = ArrayList<Any?>()

    open fun set(pList: ArrayList<*>) {
        val before = mList.clone() as ArrayList<*>
        mList.clear()
        mList.addAll(pList)
        calcDiff(before, mList)
    }

    open fun add(item: Any?) {
        val before = mList.clone() as ArrayList<*>
        mList.add(item)
        calcDiff(before, mList)
    }

    open fun addAll(pList: ArrayList<*>, index: Int? = null) {
        val before = mList.clone() as ArrayList<*>
        index?.let { mList.addAll(it, pList) } ?: mList.addAll(pList)
        calcDiff(before, mList)
    }

    override fun onBindViewHolder(holder: AppHolder, position: Int) = mList[position]?.let { holder.bind(it) }
            ?: holder.bind()

    override fun getItemCount(): Int = mList.size

    private fun calcDiff(oldList: ArrayList<*>, newList: ArrayList<*>) {
        val callback = MenuDiffUtilCallBack(oldList, newList)
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this)
    }

    inner class MenuDiffUtilCallBack(private val oldList: List<*>, private val newList: List<*>) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = oldList[oldItemPosition] == newList[newItemPosition]
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean = areItemsTheSame(oldItemPosition, newItemPosition)
    }
}

abstract class AppHolder(open val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(item: Any) {}
    open fun bind() {}
}