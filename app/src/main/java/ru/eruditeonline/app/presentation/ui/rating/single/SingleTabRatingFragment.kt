package ru.eruditeonline.app.presentation.ui.rating.single

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentSingleTabRatingBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.app.presentation.ui.rating.tab.RatingTabItemFragment

class SingleTabRatingFragment : BaseFragment(R.layout.fragment_single_tab_rating) {
    private val binding by viewBinding(FragmentSingleTabRatingBinding::bind)
    private val viewModel: SingleTabRatingViewModel by appViewModels()
    private val args: SingleTabRatingFragmentArgs by navArgs()

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        toolbar.title = getString(args.mode.titleRes)
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view_rating, RatingTabItemFragment.newInstance(args.mode))
            .commit()
        Unit
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
    }
}