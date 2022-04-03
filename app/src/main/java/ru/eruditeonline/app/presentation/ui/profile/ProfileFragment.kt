package ru.eruditeonline.app.presentation.ui.profile

import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentProfileBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    override val showBottomNavigationView: Boolean = true

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel: ProfileViewModel by appViewModels()
}
