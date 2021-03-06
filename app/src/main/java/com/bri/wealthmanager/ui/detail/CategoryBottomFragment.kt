package com.bri.wealthmanager.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bri.wealthmanager.R
import com.bri.wealthmanager.databinding.CategoryListBinding
import com.bri.wealthmanager.ui.category.AddCategoryContract
import com.bri.wealthmanager.vm.CategoryListViewModel
import com.bri.wealthmanager.vm.DetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryBottomFragment : BottomSheetDialogFragment() {
    private lateinit var binding: CategoryListBinding
    private val parentVm by activityViewModels<DetailViewModel>()
    private val vm by viewModels<CategoryListViewModel>()

    private val addCategoryContract = registerForActivityResult(AddCategoryContract()) { success ->
        if (success) vm.getCategories()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.category_list, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm
        binding.list.adapter = CategoryAdapter(
                { addCategoryContract.launch(null) },
                { category ->
                    parentVm.selectCategory(category)
                    dismiss()
                })
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