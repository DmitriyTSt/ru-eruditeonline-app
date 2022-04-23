package ru.eruditeonline.app.presentation.ui.test.successresult

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentSuccessSaveResultBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class SuccessSaveResultFragment : BaseFragment(R.layout.fragment_success_save_result) {
    private val binding by viewBinding(FragmentSuccessSaveResultBinding::bind)
    private val viewModel: SuccessSaveResultViewModel by appViewModels()
    private val args: SuccessSaveResultFragmentArgs by navArgs()

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        buttonCompetitions.setOnClickListener {
            viewModel.openCompetitions()
        }
        buttonResult.setOnClickListener {
            viewModel.openResult(args.result.id)
        }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
    }
}
