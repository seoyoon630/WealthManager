package com.bri.wealthmanager

import com.and.base.ui.BaseApplication
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class WealthApp : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        IS_DEBUG = BuildConfig.DEBUG
    }
}