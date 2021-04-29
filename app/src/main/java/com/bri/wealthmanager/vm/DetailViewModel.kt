package com.bri.wealthmanager.vm

import androidx.lifecycle.*
import com.and.base.ui.BaseViewModel
import com.bri.wealthmanager.common.NonNullMutableLiveData
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
    private var initialName: String = ""
    private var initialAmount: String = ""
    val name = NonNullMutableLiveData("")
    val amount = NonNullMutableLiveData("")

    val isReadyToConfirm = MediatorLiveData<Boolean>().apply {
        addSource(name) { this.value = isValid() }
        addSource(amount) { this.value = isValid() }
    }

    val id by lazy { savedStateHandle.get<Int>(DetailActivity.EXTRA.ID) }
    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    init {
        viewModelScope.launch {
            id?.let {
                repository.get(it)?.let { data ->
                    name.value = data.name
                    amount.value = data.amount.toString()
                    initialName = data.name
                    initialAmount = data.amount.toString()
                }
            }
        }.onProgress(_isProgress)
    }

    fun confirm() {
        id?.let { update() } ?: run { insert() }
    }

    private fun update() {
        viewModelScope.launch {
            kotlin.runCatching {
                id?.let { repository.update(it, name.value, amount.value.toDouble()) }
                    ?: run { throw Exception("Id not exist.") }
                _isSuccess.value = true
            }.onFailure {
                it.printStackTrace()
                _isSuccess.value = false
            }
        }
    }

    private fun insert() {
        viewModelScope.launch {
            runCatching {
                repository.insert(name.value, amount.value.toDouble())
                _isSuccess.value = true
            }.onFailure {
                it.printStackTrace()
                _isSuccess.value = false
            }
        }
    }

    private fun isValid(): Boolean {
        val name = name.value ?: ""
        val amount = amount.value ?: ""
        return name.isNotEmpty() && amount.isNotEmpty()
                && (name != initialName || amount != initialAmount)
    }
}