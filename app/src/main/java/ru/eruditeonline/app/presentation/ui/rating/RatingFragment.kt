package ru.eruditeonline.app.presentation.ui.rating

import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentRatingBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class RatingFragment : BaseFragment(R.layout.fragment_rating) {
    private val binding by viewBinding(FragmentRatingBinding::bind)
    private val viewModel: RatingViewModel by appViewModels()
}
