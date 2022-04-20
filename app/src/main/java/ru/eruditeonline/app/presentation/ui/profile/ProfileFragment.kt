package ru.eruditeonline.app.presentation.ui.profile

import android.os.Bundle
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentProfileBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.app.presentation.ui.profile.anonym.AnonymProfileFragment
import ru.eruditeonline.app.presentation.ui.profile.user.UserProfileFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    override val showBottomNavigationView: Boolean = true

    private val binding by viewBinding(FragmentProfileBinding::bind)
    private val viewModel: ProfileViewModel by appViewModels()

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        val profileFragment = if (viewModel.preferencesStorage.isSignedIn) {
            UserProfileFragment.newInstance()
        } else {
            AnonymProfileFragment.newInstance()
        }
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, profileFragment)
            .commit()
        Unit
    }
}
