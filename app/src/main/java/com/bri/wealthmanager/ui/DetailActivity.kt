package com.bri.wealthmanager.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bri.wealthmanager.R
import com.bri.wealthmanager.common.AppActivity
import com.bri.wealthmanager.databinding.ActivityDetailBinding
import com.bri.wealthmanager.vm.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppActivity() {
    lateinit var bb: ActivityDetailBinding
    override val vm by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bb = DataBindingUtil.setContentView(this, R.layout.activity_detail)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        bb.vm = vm
        bb.lifecycleOwner = this
        vm.isSuccess.observe(this) {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK)
            finish()
        }
    }

    class EXTRA {
        companion object {
            const val ID = "ID"
        }
    }
}