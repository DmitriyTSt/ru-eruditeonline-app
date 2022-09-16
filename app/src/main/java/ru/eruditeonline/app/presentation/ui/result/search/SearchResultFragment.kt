package ru.eruditeonline.app.presentation.ui.result.search

import android.os.Bundle
import android.text.InputType
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.navArgs
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
    private val args: SearchResultFragmentArgs by navArgs()

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setupSearch()
        setupHelpText()
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
                viewModel.search(args.mode, text)
            }
            true
        }
        inputType = when (args.mode) {
            SearchResultMode.EMAIL -> InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS
            SearchResultMode.QUERY -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        }
        hint = when (args.mode) {
            SearchResultMode.EMAIL -> getString(R.string.search_result_by_email_hint)
            SearchResultMode.QUERY -> getString(R.string.search_result_hint)
        }
        doAfterTextChanged {
            binding.imageViewClose.isVisible = !it.isNullOrEmpty()
        }
        activity?.showSoftKeyboard(this)
    }

    private fun setupHelpText() = with(binding) {
        textViewEmailSearchHelp.isVisible = args.mode == SearchResultMode.EMAIL
    }
}
