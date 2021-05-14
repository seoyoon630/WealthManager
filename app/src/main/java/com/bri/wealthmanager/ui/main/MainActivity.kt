package com.bri.wealthmanager.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bri.wealthmanager.R
import com.bri.wealthmanager.common.AppActivity
import com.bri.wealthmanager.databinding.ActivityMainBinding
import com.bri.wealthmanager.ui.detail.DetailActivity
import com.bri.wealthmanager.ui.detail.DetailContract
import com.bri.wealthmanager.vm.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppActivity() {
    lateinit var binding: ActivityMainBinding
    override val vm by viewModels<MainViewModel>()

    private val detailContract = registerForActivityResult(DetailContract()) {
        if (it) vm.getAll()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        binding.vm = vm
        binding.lifecycleOwner = this
        binding.list.adapter = AssetAdapter { id -> id?.let { detailContract.launch(it) } }

        vm.startActivity.observe(this) { pair ->
            when (pair.first) {
                DetailActivity::class.java -> detailContract.launch(null)
                else -> startActivity(Intent(this, pair.first).apply {
                    pair.second?.let { bundle -> putExtras(bundle) }
                })
            }
        }
    }
}