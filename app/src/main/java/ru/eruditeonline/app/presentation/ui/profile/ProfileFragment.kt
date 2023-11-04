package ru.eruditeonline.app.presentation.ui.profile

import android.os.Bundle
import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.app.presentation.ui.profile.anonym.AnonymProfileFragment
import ru.eruditeonline.app.presentation.ui.profile.user.UserProfileFragment

class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    override val showBottomNavigationView: Boolean = true

    private val viewModel: ProfileViewModel by appViewModels()

    override fun setupLayout(savedInstanceState: Bundle?) {
        super.setupLayout(savedInstanceState)
        viewModel.resolveAuthState()
    }

    override fun onBindViewModel() {
        viewModel.isAuthorizedLiveData.observe { isAuthorized ->
            val fragment = if (isAuthorized) {
                UserProfileFragment.newInstance()
            } else {
                AnonymProfileFragment.newInstance()
            }
            childFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_view, fragment)
                .commit()
            Unit
        }
    }
}
