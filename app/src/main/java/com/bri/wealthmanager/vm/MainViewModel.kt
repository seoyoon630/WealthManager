package com.bri.wealthmanager.vm

import android.os.Bundle
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.and.base.ui.BaseViewModel
import com.bri.wealthmanager.common.NonNullMutableLiveData
import com.bri.wealthmanager.common.onProgress
import com.bri.wealthmanager.repo.MainRepository
import com.bri.wealthmanager.ui.DetailActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : BaseViewModel() {
    private val _startActivity = MutableLiveData<Pair<Class<*>, Bundle?>>()
    val startActivity: LiveData<Pair<Class<*>, Bundle?>> get() = _startActivity

    val showLottieAnimation = NonNullMutableLiveData(false)

    val list = ObservableField<ArrayList<*>>(arrayListOf<Any>())

    init {
        getAll()
    }

    fun startDetailActivity() {
        _startActivity.value = Pair(DetailActivity::class.java, null)
    }

    fun getAll() {
        viewModelScope.launch {
            repository.getAll().let {
                list.set(it)
                showLottieAnimation.value = it.isEmpty()
            }
        }.onProgress(_isProgress)
    }
}