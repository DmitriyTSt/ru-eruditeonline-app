package ru.eruditeonline.app.presentation.ui.profile.user

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.profile.Profile
import ru.eruditeonline.app.databinding.FragmentUserProfileBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.errorSnackbar
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
            viewModel.openSearchResultsByEmail()
        }
        textViewUserResults.setOnClickListener {
            viewModel.openUserResults()
        }
        imageViewLogout.setOnClickListener {
            viewModel.logout()
        }
        textViewSettings.setOnClickListener {
            viewModel.openSettings()
        }
        textViewInfo.setOnClickListener {
            viewModel.openInformation()
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
            binding.imageViewLogout.isVisible = !state.isLoading
            binding.progressBarLogout.isVisible = state.isLoading
            state.doOnSuccess {
                reloadStack()
            }
            state.doOnError { error ->
                errorSnackbar(error)
            }
        }
    }

    override fun applyBottomNavigationViewPadding(view: View, bottomNavigationViewHeight: Int) = Unit

    private fun bindProfile(profile: Profile) = with(binding) {
        imageViewAvatar.load(profile.avatar, placeHolderRes = R.drawable.img_logo)
        textViewUserName.text = listOf(profile.surname, profile.name, profile.patronymic)
            .filter { !it.isNullOrEmpty() }
            .joinToString(" ")
        imageViewCountry.isVisible = profile.country != null
        if (imageViewCountry.isVisible) {
            imageViewCountry.load(profile.country?.image)
        }
        textViewLocation.text = if (profile.country != null) {
            "${profile.country.name}, ${profile.city}"
        } else {
            profile.city
        }
    }
}
