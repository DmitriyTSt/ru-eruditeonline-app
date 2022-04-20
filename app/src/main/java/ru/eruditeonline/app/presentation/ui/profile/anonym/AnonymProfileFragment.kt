package ru.eruditeonline.app.presentation.ui.profile.anonym

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResultListener
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentAnonymProfileBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.auth.login.LoginFragment
import ru.eruditeonline.app.presentation.ui.base.BaseFragment

class AnonymProfileFragment : BaseFragment(R.layout.fragment_anonym_profile) {

    companion object {
        fun newInstance(): AnonymProfileFragment {
            return AnonymProfileFragment()
        }
    }

    private val binding by viewBinding(FragmentAnonymProfileBinding::bind)
    private val viewModel: AnonymProfileViewModel by appViewModels()

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        root.fitTopInsetsWithPadding()
        buttonLogin.setOnClickListener {
            viewModel.openLogin()
        }
        buttonRegistration.setOnClickListener {
            viewModel.openRegistration()
        }
        textViewCommonResults.setOnClickListener {
            viewModel.openCommonResults()
        }
        textViewSearchResultsByEmail.setOnClickListener {
            viewModel.openSearchResultsByEmail()
        }
        setFragmentResultListener(LoginFragment.REQUEST_CODE) { _, _ ->
            viewModel.reloadStack()
        }
        textViewInfo.setOnClickListener {
            viewModel.openInformation()
        }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
    }

    override fun applyBottomNavigationViewPadding(view: View, bottomNavigationViewHeight: Int) = Unit
}
