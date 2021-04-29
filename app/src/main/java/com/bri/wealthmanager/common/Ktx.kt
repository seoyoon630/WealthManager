package com.bri.wealthmanager.common

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Job
import java.text.DecimalFormat

fun Job.onProgress(progress: MutableLiveData<Boolean>) {
    progress.postValue(true)
    this.invokeOnCompletion { progress.postValue(false) }
}

fun Double.convertToDisplayAmount(): String {
    return DecimalFormat("#,###.##").format(this) + "원"
}