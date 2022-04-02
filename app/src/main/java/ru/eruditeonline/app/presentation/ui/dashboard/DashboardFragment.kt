package ru.eruditeonline.app.presentation.ui.dashboard

import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentDashboardBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class DashboardFragment : BaseFragment(R.layout.fragment_dashboard) {

    override val showBottomNavigationView: Boolean = true

    private val binding by viewBinding(FragmentDashboardBinding::bind)
    private val viewModel: DashboardViewModel by appViewModels()
}
