package ru.eruditeonline.app.presentation.ui.auth.login

import android.os.Bundle
import android.view.ViewGroup
import androidx.core.graphics.Insets
import androidx.core.os.bundleOf
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.core.view.updatePadding
import androidx.fragment.app.setFragmentResult
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentLoginBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.doOnApplyWindowInsets
import ru.eruditeonline.app.presentation.extension.errorSnackbar
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.navigation.observeNavigationCommands

class LoginFragment : BaseFragment(R.layout.fragment_login) {

    companion object {
        const val REQUEST_CODE = "login_request_code"
    }

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: LoginViewModel by appViewModels()

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setupInsets()
        buttonLogin.setOnClickListener {
            viewModel.login(textInputLayoutLogin, textInputLayoutPassword)
        }
        buttonRegistration.setOnClickListener {
            viewModel.openRegistration()
        }
        buttonRestorePassword.setOnClickListener {
            viewModel.openRestorePassword()
        }
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        loginLiveEvent.observe { state ->
            binding.buttonLogin.setState(state)
            state.doOnError { error ->
                errorSnackbar(error)
            }
            state.doOnSuccess {
                setFragmentResult(REQUEST_CODE, bundleOf())
                viewModel.navigateBack()
            }
        }
    }

    private fun setupInsets() = with(binding) {
        root.doOnApplyWindowInsets { _, insets, _ ->
            val windowInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            toolbar.updatePadding(top = windowInsets.top)
            linearLayoutContent.updatePadding(top = windowInsets.top)
            nestedScrollView.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                updateMargins(bottom = windowInsets.bottom)
            }
            WindowInsetsCompat.Builder().setInsets(
                WindowInsetsCompat.Type.systemBars(),
                Insets.of(
                    windowInsets.left,
                    0,
                    windowInsets.right,
                    0,
                )
            ).build()
        }
    }
}
