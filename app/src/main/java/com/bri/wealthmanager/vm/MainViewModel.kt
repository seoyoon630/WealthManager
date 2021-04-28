package com.bri.wealthmanager.vm

import android.os.Bundle
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.and.base.ui.BaseViewModel
import com.bri.wealthmanager.db.entity.AssetEntity
import com.bri.wealthmanager.repo.MainRepository
import com.bri.wealthmanager.ui.DetailActivity
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : BaseViewModel() {
    private val _startActivity = MutableLiveData<Pair<Class<*>, Bundle?>>()
    val startActivity: LiveData<Pair<Class<*>, Bundle?>> get() = _startActivity

    val list = ObservableField<ArrayList<AssetEntity>>(arrayListOf())

    init {
        Logger.w("init MainViewModel $repository")
        getAll()
    }

    fun startDetailActivity() {
        _startActivity.value = Pair(DetailActivity::class.java, null)
    }

    fun getAll() {
        viewModelScope.launch {
            list.set(repository.getAll())
        }
    }
}