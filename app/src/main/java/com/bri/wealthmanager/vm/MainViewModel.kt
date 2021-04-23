package com.bri.wealthmanager.vm

import androidx.lifecycle.ViewModel
import com.bri.wealthmanager.repo.MainRepository
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    init {
        Logger.w("init MainViewModel $repository")
    }
}