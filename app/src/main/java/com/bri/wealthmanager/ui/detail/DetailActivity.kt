package com.bri.wealthmanager.ui.detail

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
    lateinit var binding: ActivityDetailBinding
    override val vm by viewModels<DetailViewModel>()

    private val categoryDialog by lazy { CategoryBottomFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        binding.vm = vm
        binding.lifecycleOwner = this

        vm.showCategoryDialog.observe(this) {
            if (!categoryDialog.isAdded) {
                categoryDialog.show(supportFragmentManager, null)
            }
        }

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