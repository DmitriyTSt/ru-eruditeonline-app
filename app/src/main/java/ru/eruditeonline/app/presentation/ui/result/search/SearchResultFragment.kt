package ru.eruditeonline.app.presentation.ui.result.search

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentSearchResultBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.extension.hideSoftKeyboard
import ru.eruditeonline.app.presentation.extension.showSoftKeyboard
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class SearchResultFragment : BaseFragment(R.layout.fragment_search_result) {
    private val binding by viewBinding(FragmentSearchResultBinding::bind)
    private val viewModel: SearchResultViewModel by appViewModels()

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setupSearch()
        imageViewClose.setOnClickListener {
            editTextSearch.setText("")
        }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
    }

    private fun setupSearch() = with(binding.editTextSearch) {
        setOnEditorActionListener { _, _, _ ->
            activity?.hideSoftKeyboard()
            viewModel.search(text.toString())
            true
        }
        doAfterTextChanged {
            binding.imageViewClose.isVisible = !it.isNullOrEmpty()
        }
        activity?.showSoftKeyboard(this)
    }
}
