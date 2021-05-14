package com.bri.wealthmanager.vm

import android.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.and.base.common.Event
import com.and.base.ui.BaseViewModel

class CategoryViewModel : BaseViewModel() {
    private val rawList = listOf("#FAF1D6","#FAD4AE","#FDAFAB","#FADEE1","#D9F1F1","#B6E3E9")
    val colorList = ArrayList<Int>(rawList.map { Color.parseColor(it) })

    private val _showColorDialog = MutableLiveData<Event<Unit>>()
    val showColorDialog: LiveData<Event<Unit>> get() = _showColorDialog

    fun showColorDialog() {
        _showColorDialog.value = Event(Unit)
    }

    fun selectColor(color: Int) {
        // todo
    }
}