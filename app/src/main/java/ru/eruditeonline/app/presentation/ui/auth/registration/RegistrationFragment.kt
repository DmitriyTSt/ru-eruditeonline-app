package ru.eruditeonline.app.presentation.ui.auth.registration

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.core.graphics.Insets
import androidx.core.text.buildSpannedString
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.setFragmentResultListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.auth.Gender
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.databinding.FragmentRegistrationBinding
import ru.eruditeonline.app.presentation.extension.errorSnackbar
import ru.eruditeonline.app.presentation.managers.DateFormatter
import ru.eruditeonline.app.presentation.ui.country.SelectCountryFragment
import ru.eruditeonline.navigation.observeNavigationCommands
import ru.eruditeonline.ui.presentation.base.BaseFragment
import ru.eruditeonline.ui.presentation.ext.appViewModels
import ru.eruditeonline.ui.presentation.ext.doOnApplyWindowInsets
import ru.eruditeonline.ui.presentation.ext.getColorCompat
import ru.eruditeonline.ui.presentation.ext.link
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {
    private val binding by viewBinding(FragmentRegistrationBinding::bind)
    private val viewModel: RegistrationViewModel by appViewModels()

    @Inject lateinit var dateFormatter: DateFormatter

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        setupInsets()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setupPrivacyPolicy()
        setupSelectBirthday()
        setupSelectCountry()
        setRequiredFieldHints()
        setupRegistrationButton()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        registrationLiveEvent.observe { state ->
            binding.buttonRegistration.setState(state)
            state.doOnError { error ->
                errorSnackbar(error)
            }
        }
        birthdayLiveData.observe { millis ->
            binding.editTextBirthday.setText(
                dateFormatter.formatStandardDate(
                    Instant.ofEpochSecond(millis / 1000).atZone(ZoneId.systemDefault()).toLocalDate()
                )
            )
        }
        countryLiveData.observe { country ->
            binding.editTextCountry.setText(country.name)
        }
        scrollToErrorFieldLiveEvent.observe { viewId ->
            scrollToErrorViewById(viewId)
        }
        emailValidationErrorLiveEvent.observe {
            binding.textInputLayoutEmail.error = getString(R.string.email_validation_error)
        }
        passwordConfirmErrorLiveEvent.observe {
            binding.textInputLayoutPasswordConfirm.error = getString(R.string.password_confirm_error)
        }
        privacyPolicyErrorLiveEvent.observe {
            errorSnackbar(getString(R.string.privacy_policy_error))
        }
    }

    private fun setupInsets() = with(binding) {
        root.doOnApplyWindowInsets { _, insets, _ ->
            val windowInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            toolbar.updatePadding(
                top = windowInsets.top,
            )
            root.updatePadding(
                bottom = windowInsets.bottom
            )
            WindowInsetsCompat.Builder().setInsets(
                WindowInsetsCompat.Type.systemBars(),
                Insets.of(
                    windowInsets.left,
                    0,
                    windowInsets.right,
                    0
                )
            ).build()
        }
    }

    private fun setupPrivacyPolicy() = with(binding.textViewPrivacyPolicy) {
        movementMethod = LinkMovementMethod.getInstance()
        text = buildSpannedString {
            append(getString(R.string.registration_privacy_policy_checkbox_text_part_start))
            append(" ")
            link(viewModel::openPersonalData) {
                append(getString(R.string.privacy_policy_checkbox_text_part_link))
            }
            append(" ")
            append(getString(R.string.privacy_policy_checkbox_text_part_end))
        }
    }

    private fun setupSelectCountry() = with(binding) {
        viewSelectCountry.setOnClickListener {
            viewModel.openSelectCountry()
        }
        setFragmentResultListener(SelectCountryFragment.REQUEST_CODE) { _, bundle ->
            val country = bundle.getParcelable<Country?>(SelectCountryFragment.KEY_COUNTRY)
            if (country != null) {
                viewModel.selectCountry(country)
            }
        }
    }

    private fun setupSelectBirthday() = with(binding) {
        viewSelectBirthday.setOnClickListener {
            MaterialDatePicker.Builder.datePicker()
                .setTitleText(textInputLayoutBirthday.hint)
                .apply {
                    val selected = viewModel.birthdayLiveData.value
                    if (selected != null) {
                        setSelection(selected)
                    }
                }
                .setCalendarConstraints(
                    CalendarConstraints.Builder().setValidator(DateValidatorPointBackward.now()).build()
                )
                .build()
                .apply {
                    addOnPositiveButtonClickListener { millis ->
                        viewModel.selectBirthday(millis)
                    }
                }
                .show(childFragmentManager, "birthday")
        }
    }

    private fun setRequiredFieldHints() = with(binding) {
        textInputLayoutSurname.setFieldRequiredHint()
        textInputLayoutName.setFieldRequiredHint()
        textInputLayoutCity.setFieldRequiredHint()
        textInputLayoutCountry.setFieldRequiredHint()
        textInputLayoutEmail.setFieldRequiredHint()
        textInputLayoutPassword.setFieldRequiredHint()
        textInputLayoutPasswordConfirm.setFieldRequiredHint()
    }

    private fun TextInputLayout.setFieldRequiredHint() {
        val oldHint = hint.toString()
        val newHint = SpannableStringBuilder(oldHint)
        newHint.append(
            SpannableString("*").apply {
                setSpan(ForegroundColorSpan(context.getColorCompat(R.color.red)), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        )
        hint = newHint
    }

    private fun setupRegistrationButton() = with(binding) {
        buttonRegistration.setOnClickListener {
            val gender = when (radioGroupGender.checkedRadioButtonId) {
                R.id.radioButtonGenderMale -> Gender.MALE
                R.id.radioButtonGenderFemale -> Gender.FEMALE
                else -> Gender.NOT_SET
            }
            viewModel.register(
                surname = textInputLayoutSurname,
                name = textInputLayoutName,
                patronymic = textInputLayoutPatronymic,
                gender = gender,
                school = textInputLayoutSchool,
                city = textInputLayoutCity,
                region = textInputLayoutRegion,
                country = textInputLayoutCountry,
                email = textInputLayoutEmail,
                password = textInputLayoutPassword,
                passwordConfirm = textInputLayoutPasswordConfirm,
                isNotificationChecked = checkboxNotifications.isChecked,
                isPrivacyPolicyChecked = checkboxPrivacyPolicy.isChecked,
            )
        }
    }

    private fun scrollToErrorViewById(viewId: Int) = with(binding) {
        val viewWithError = root.findViewById<View>(viewId)
        val viewTop = if (viewId == R.id.textInputLayoutCountry) {
            binding.constraintLayoutCountry.top
        } else {
            viewWithError.top
        }
        nestedScrollView.scrollTo(0, viewTop)
    }
}
