package com.bri.wealthmanager.vm

import androidx.lifecycle.*
import com.and.base.ui.BaseViewModel
import com.bri.wealthmanager.common.onProgress
import com.bri.wealthmanager.repo.DetailRepository
import com.bri.wealthmanager.ui.DetailActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {

    val name = MutableLiveData("")
    val amount = MutableLiveData("")
    val id by lazy { savedStateHandle.get<Int>(DetailActivity.EXTRA.ID) }
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    init {
        viewModelScope.launch {
            id?.let {
                repository.get(it)?.let { data ->
                    name.value = data.name
                    amount.value = data.amount.toString()
                }
            }
        }.onProgress(_isProgress)
    }

    fun insert() {
        viewModelScope.launch {
            runCatching {
                repository.insert(name.value ?: "", (amount.value ?: "0").toDouble())
                _isSuccess.value = true
            }.onFailure {
                it.printStackTrace()
                _isSuccess.value = false
            }
        }
    }
}