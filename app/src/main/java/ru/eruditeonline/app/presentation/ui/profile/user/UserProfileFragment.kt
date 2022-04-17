package ru.eruditeonline.app.presentation.ui.profile.user

import ru.eruditeonline.app.R
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class UserProfileFragment : BaseFragment(R.layout.fragment_user_profile) {

    companion object {
        fun newInstance(): UserProfileFragment {
            return UserProfileFragment()
        }
    }
}