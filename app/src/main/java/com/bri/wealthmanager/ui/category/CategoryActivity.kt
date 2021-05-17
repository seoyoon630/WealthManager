package com.bri.wealthmanager.ui.category

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.bri.wealthmanager.R
import com.bri.wealthmanager.common.AppActivity
import com.bri.wealthmanager.databinding.ActivityCategoryBinding
import com.bri.wealthmanager.ui.detail.CategoryBottomFragment
import com.bri.wealthmanager.vm.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryActivity : AppActivity() {
    lateinit var binding: ActivityCategoryBinding
    override val vm by viewModels<CategoryViewModel>()

    private val colorDialog by lazy { ColorBottomFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category)
    }

    override fun onLoadOnce() {
        super.onLoadOnce()
        binding.lifecycleOwner = this
        binding.vm = vm

        vm.showColorDialog.observe(this) {
            if (!colorDialog.isAdded)
                colorDialog.show(supportFragmentManager, null)
        }

        vm.isSuccess.observe(this) {
            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            setResult(RESULT_OK)
            finish()
        }
    }
}