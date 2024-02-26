package ru.eruditeonline.app.presentation.ui.rating

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentRatingBinding
import ru.eruditeonline.navigation.observeNavigationCommands
import ru.eruditeonline.ui.presentation.base.BaseFragment
import ru.eruditeonline.ui.presentation.ext.appViewModels
import ru.eruditeonline.ui.presentation.ext.fitTopInsetsWithPadding

class RatingFragment : BaseFragment(R.layout.fragment_rating) {

    override val showBottomNavigationView: Boolean = true

    private val binding by viewBinding(FragmentRatingBinding::bind)
    private val viewModel: RatingViewModel by appViewModels()

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        appBarLayout.fitTopInsetsWithPadding()
        setupViewPager()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
    }

    override fun applyBottomNavigationViewPadding(view: View, bottomNavigationViewHeight: Int) = Unit

    private fun setupViewPager() = with(binding) {
        viewPager.adapter = RatingPagerAdapter(this@RatingFragment)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(RatingPagerAdapter.getTitleRes(position))
        }.attach()
    }
}
