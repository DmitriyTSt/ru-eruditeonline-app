package ru.eruditeonline.app.presentation.ui.profile.user

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.profile.Profile
import ru.eruditeonline.app.databinding.FragmentUserProfileBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.extension.load
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class UserProfileFragment : BaseFragment(R.layout.fragment_user_profile) {

    companion object {
        fun newInstance(): UserProfileFragment {
            return UserProfileFragment()
        }
    }

    private val binding by viewBinding(FragmentUserProfileBinding::bind)
    private val viewModel: UserProfileViewModel by appViewModels()

    override fun callOperations() {
        viewModel.loadProfile()
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        root.fitTopInsetsWithPadding()
        stateViewFlipper.setRetryMethod { viewModel.loadProfile() }
        textViewCommonResults.setOnClickListener {
            viewModel.openCommonResults()
        }
        textViewSearchResultsByQuery.setOnClickListener {
            viewModel.openSearchResultsByQuery()
        }
        textViewUserResults.setOnClickListener {
            viewModel.openUserResults()
        }
        imageViewLogout.setOnClickListener {
            viewModel.logout()
        }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        profileLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
            state.doOnSuccess { profile ->
                bindProfile(profile)
            }
        }
        logoutLiveEvent.observe { state ->
            state.doOnSuccess {
                reloadStack()
            }
        }
    }

    override fun applyBottomNavigationViewPadding(view: View, bottomNavigationViewHeight: Int) = Unit

    private fun bindProfile(profile: Profile) = with(binding) {
        imageViewAvatar.load(profile.avatar)
        textViewUserName.text = listOf(profile.surname, profile.name, profile.patronymic)
            .filter { !it.isNullOrEmpty() }
            .joinToString(" ")
    }
}