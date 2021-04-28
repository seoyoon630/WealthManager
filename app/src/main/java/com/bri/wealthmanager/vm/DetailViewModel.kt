package com.bri.wealthmanager.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.and.base.ui.BaseViewModel
import com.bri.wealthmanager.repo.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) :
    BaseViewModel() {

    var name = ""
    var amount = ""
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    fun insert() {
        viewModelScope.launch {
            runCatching {
                repository.insert(name, amount.toDouble())
                _isSuccess.value = true
            }.onFailure {
                it.printStackTrace()
                _isSuccess.value = false
            }
        }
    }
}