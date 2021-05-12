package com.bri.wealthmanager.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bri.wealthmanager.R
import com.bri.wealthmanager.databinding.SelectCategoryBinding
import com.bri.wealthmanager.vm.CategoryViewModel
import com.bri.wealthmanager.vm.DetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryBottomFragment : BottomSheetDialogFragment() {
    private lateinit var bb: SelectCategoryBinding
    private val parentVm by activityViewModels<DetailViewModel>()
    private val vm by viewModels<CategoryViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        bb = DataBindingUtil.inflate(inflater, R.layout.select_category, container, false)
        return bb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bb.lifecycleOwner = this
        bb.vm = vm
        bb.list.adapter = CategoryAdapter { category ->
            parentVm.selectCategory(category)
            dismiss()
        }
    }

    companion object {
        fun newInstance(): CategoryBottomFragment {
            val args = Bundle()

            val fragment = CategoryBottomFragment()
            fragment.arguments = args
            return fragment
        }
    }
}