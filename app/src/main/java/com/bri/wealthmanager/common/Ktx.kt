package com.bri.wealthmanager.common

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Job

fun Job.onProgress(progress: MutableLiveData<Boolean>) {
    progress.postValue(true)
    this.invokeOnCompletion { progress.postValue(false) }
}