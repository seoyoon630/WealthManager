package com.bri.wealthmanager

import com.and.base.ui.BaseApplication
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class WealthApp : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        IS_DEBUG = BuildConfig.DEBUG
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(AndroidLogAdapter())
        }
    }
}