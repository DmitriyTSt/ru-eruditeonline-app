package ru.eruditeonline.app.presentation.ui.rating

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.eruditeonline.app.presentation.ui.rating.tab.RatingTabItemFragment
import ru.eruditeonline.app.presentation.ui.rating.tab.RatingTabItemMode
import ru.eruditeonline.ui.presentation.base.BaseFragment

class RatingPagerAdapter(fragment: BaseFragment) : FragmentStateAdapter(fragment) {

    companion object {
        fun getTitleRes(position: Int) = RatingTabItemMode.values()[position].titleRes
    }

    override fun getItemCount(): Int = RatingTabItemMode.values().size

    override fun createFragment(position: Int): Fragment {
        return RatingTabItemFragment.newInstance(RatingTabItemMode.values()[position])
    }
}
