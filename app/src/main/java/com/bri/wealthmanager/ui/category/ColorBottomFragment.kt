package com.bri.wealthmanager.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bri.wealthmanager.R
import com.bri.wealthmanager.databinding.CategoryListBinding
import com.bri.wealthmanager.databinding.ColorListBinding
import com.bri.wealthmanager.vm.CategoryListViewModel
import com.bri.wealthmanager.vm.CategoryViewModel
import com.bri.wealthmanager.vm.DetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ColorBottomFragment : BottomSheetDialogFragment() {
    private lateinit var binding: ColorListBinding
    private val vm by activityViewModels<CategoryViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.color_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        binding.list.adapter = ColorAdapter { color ->
            vm.selectColor(color)
            dismiss()
        }
    }

    companion object {
        fun newInstance(): ColorBottomFragment {
            val args = Bundle()

            val fragment = ColorBottomFragment()
            fragment.arguments = args
            return fragment
        }
    }
}