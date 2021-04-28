package com.bri.wealthmanager.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.and.base.ui.BaseActivity
import com.bri.wealthmanager.R
import com.bri.wealthmanager.databinding.ActivityMainBinding
import com.bri.wealthmanager.vm.MainViewModel
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    lateinit var bb: ActivityMainBinding
    override val vm by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(this, R.layout.activity_main)

        Logger.w("onCreate MainActivity $vm")
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        bb.vm = vm
        bb.list.adapter = AssetAdapter()

        vm.startActivity.observe(this) { pair ->
            startActivity(Intent(this, pair.first).apply {
                pair.second?.let { bundle -> putExtras(bundle) }
            })
        }
    }
}