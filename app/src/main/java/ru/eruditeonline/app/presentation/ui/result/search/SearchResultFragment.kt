package ru.eruditeonline.app.presentation.ui.result.search

import android.os.Bundle
import android.text.InputType
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentSearchResultBinding
import ru.eruditeonline.navigation.observeNavigationCommands
import ru.eruditeonline.ui.presentation.base.BaseFragment
import ru.eruditeonline.ui.presentation.ext.appViewModels
import ru.eruditeonline.ui.presentation.ext.fitTopInsetsWithPadding
import ru.eruditeonline.ui.presentation.ext.hideSoftKeyboard
import ru.eruditeonline.ui.presentation.ext.showSoftKeyboard

/**
 * Поиск результатов по email
 */
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
            val text = text.toString()
            if (text.isNotEmpty()) {
                activity?.hideSoftKeyboard()
                viewModel.search(text)
            }
            true
        }
        inputType = InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
        hint = getString(R.string.search_result_by_email_hint)
        doAfterTextChanged {
            binding.imageViewClose.isVisible = !it.isNullOrEmpty()
        }
        activity?.showSoftKeyboard(this)
    }
}
