package ru.eruditeonline.app.presentation.ui.competition.items

import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentCompetitionItemsBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class CompetitionItemsFragment : BaseFragment(R.layout.fragment_competition_items) {
    private val binding by viewBinding(FragmentCompetitionItemsBinding::bind)
    private val viewModel: CompetitionItemsViewModel by appViewModels()
}
