package com.bri.wealthmanager.vm

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.and.base.common.Event
import com.and.base.ui.BaseViewModel
import com.bri.wealthmanager.common.NonNullMutableLiveData
import com.bri.wealthmanager.repo.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository)
    : BaseViewModel() {
    private val initialName = ""
    private val initialColor = "색상 선택"
    val name = NonNullMutableLiveData(initialName)
    val color = NonNullMutableLiveData(initialColor)

    val isReadyToConfirm = MediatorLiveData<Boolean>().apply {
        addSource(name) { value = isValid() }
        addSource(color) { value = isValid() }
    }

    private val rawList = listOf("#FAF1D6", "#FAD4AE", "#FDAFAB", "#FADEE1", "#D9F1F1", "#B6E3E9")
    val colorList = ArrayList<String>(rawList)

    private val _showColorDialog = MutableLiveData<Event<Unit>>()
    val showColorDialog: LiveData<Event<Unit>> get() = _showColorDialog

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> get() = _isSuccess

    fun showColorDialog() {
        _showColorDialog.value = Event(Unit)
    }

    fun selectColor(color: String) {
        this.color.value = color
    }

    fun confirm() {
        insert()
    }

    private fun insert() {
        viewModelScope.launch {
            runCatching {
                repository.insert(name.value, Color.parseColor(color.value))
                _isSuccess.value = true
            }.onFailure {
                it.printStackTrace()
                _isSuccess.value = false
            }
        }
    }

    private fun isValid(): Boolean {
        val name = name.value
        val color = color.value
        return name.isNotEmpty() && name != initialName
                && color.isNotEmpty() && color != initialColor
    }

}