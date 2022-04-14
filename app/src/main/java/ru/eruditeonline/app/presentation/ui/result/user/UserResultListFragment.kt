package ru.eruditeonline.app.presentation.ui.result.user

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentUserResultListBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class UserResultListFragment : BaseFragment(R.layout.fragment_user_result_list) {
    private val binding by viewBinding(FragmentUserResultListBinding::bind)
    private val viewModel: UserResultListViewModel by appViewModels()
    private val args: UserResultListFragmentArgs by navArgs()

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        setupToolbar()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
    }

    private fun setupToolbar() = with(binding) {
        val params = args.params
        toolbar.apply {
            fitTopInsetsWithPadding()
            setNavigationOnClickListener {
                viewModel.navigateBack()
            }
            title = when (params) {
                UserResultParams.All -> getString(R.string.user_result_list_title)
                else -> ""
            }
        }
        linearLayoutSearch.isVisible = params !is UserResultParams.All
        editTextSearch.setText(
            when (params) {
                UserResultParams.All -> ""
                is UserResultParams.Email -> params.email
                is UserResultParams.Query -> params.query
            }
        )
        editTextSearch.setOnClickListener {
            viewModel.navigateBack()
        }
        imageViewClose.setOnClickListener {
            viewModel.navigateBack()
        }
    }
}
