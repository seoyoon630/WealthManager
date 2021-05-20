package com.bri.wealthmanager.vm

import androidx.lifecycle.*
import com.and.base.common.Event
import com.and.base.ui.BaseViewModel
import com.bri.wealthmanager.common.NonNullMutableLiveData
import com.bri.wealthmanager.common.onProgress
import com.bri.wealthmanager.data.Category
import com.bri.wealthmanager.repo.DetailRepository
import com.bri.wealthmanager.ui.detail.DetailActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
        private val repository: DetailRepository,
        private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    val id by lazy { savedStateHandle.get<Int>(DetailActivity.EXTRA.ID) }

    private var initialName: String? = null
    private var initialAmount: String? = null
    private var initialCategory: Category? = null

    val name = NonNullMutableLiveData("")
    val amount = NonNullMutableLiveData("")
    val category = MutableLiveData<Category>(null)

    val isReadyToConfirm = MediatorLiveData<Boolean>().apply {
        addSource(name) { this.value = isValid() }
        addSource(amount) { this.value = isValid() }
        addSource(category) { this.value = isValid() }
    }

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    private val _showCategoryDialog = MutableLiveData<Event<Unit>>()
    val showCategoryDialog: LiveData<Event<Unit>> get() = _showCategoryDialog

    init {
        viewModelScope.launch {
            id?.let {
                repository.get(it)?.let { data ->
                    name.value = data.name
                    amount.value = data.amount.toString()
                    category.value = data.category
                    initialName = data.name
                    initialAmount = data.amount.toString()
                    initialCategory = data.category
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
                id?.let {
                    repository.update(it, name.value, amount.value.toDouble(), category.value)
                } ?: run { throw Exception("Id not exist.") }
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
                repository.insert(name.value, amount.value.toDouble(), category.value)
                _isSuccess.value = true
            }.onFailure {
                it.printStackTrace()
                _isSuccess.value = false
            }
        }
    }

    private fun isValid(): Boolean {
        val name = name.value
        val amount = amount.value
        val category = category.value
        return name.isNotEmpty() && amount.isNotEmpty() && category != null &&
                (name != initialName || amount != initialAmount || category != initialCategory)
    }

    fun showCategoryDialog() {
        _showCategoryDialog.value = Event(Unit)
    }

    fun selectCategory(category: Category) {
        this.category.value = category
    }
}