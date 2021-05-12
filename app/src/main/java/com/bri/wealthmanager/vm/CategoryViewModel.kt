package com.bri.wealthmanager.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.and.base.log.Log
import com.and.base.ui.BaseViewModel
import com.bri.wealthmanager.entity.CategoryEntity
import com.bri.wealthmanager.repo.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository)
    : BaseViewModel() {

    val categoryList = MutableLiveData<ArrayList<CategoryEntity>>(ArrayList())

    init {
        Log.w("CategoryViewModel initialized")
        getCategories()
    }

    fun getCategories() {
        viewModelScope.launch {
            Log.w("getCategories start")
            val result = repository.getCategories()
            Log.w("result.size = ${result.size}")
            result.forEachIndexed { index, it -> Log.w("$index $it") }
            categoryList.value = result
            Log.w("getCategories end")
        }
    }
}