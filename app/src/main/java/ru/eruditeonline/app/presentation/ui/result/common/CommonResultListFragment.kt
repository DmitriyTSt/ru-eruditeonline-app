package ru.eruditeonline.app.presentation.ui.result.common

import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentCommonResultListBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class CommonResultListFragment : BaseFragment(R.layout.fragment_common_result_list) {
    private val binding by viewBinding(FragmentCommonResultListBinding::bind)
    private val viewModel: CommonResultListViewModel by appViewModels()
}