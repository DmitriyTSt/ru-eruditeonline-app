package ru.eruditeonline.app.presentation.ui.competition.detail

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentCompetitionDetailBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class CompetitionDetailFragment : BaseFragment(R.layout.fragment_competition_detail) {
    private val binding by viewBinding(FragmentCompetitionDetailBinding::bind)
    private val viewModel: CompetitionDetailViewModel by appViewModels()
    private val args: CompetitionDetailFragmentArgs by navArgs()

    override fun callOperations() {
        viewModel.loadCompetitionItem(args.id)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
    }
}
