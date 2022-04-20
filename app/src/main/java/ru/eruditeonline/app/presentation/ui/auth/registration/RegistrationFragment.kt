package ru.eruditeonline.app.presentation.ui.auth.registration

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.setFragmentResultListener
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputLayout
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.databinding.FragmentRegistrationBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.extension.getColorCompat
import ru.eruditeonline.app.presentation.extension.setTextFromHtml
import ru.eruditeonline.app.presentation.extension.stripUnderlines
import ru.eruditeonline.app.presentation.managers.DateFormatter
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.app.presentation.ui.country.SelectCountryFragment
import java.time.Instant
import java.time.ZoneId
import javax.inject.Inject

class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {
    private val binding by viewBinding(FragmentRegistrationBinding::bind)
    private val viewModel: RegistrationViewModel by appViewModels()

    @Inject lateinit var dateFormatter: DateFormatter

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setupPrivacyPolicy()
        setupSelectBirthday()
        setupSelectCountry()
        setRequiredFieldHints()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
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
    }

    private fun setupPrivacyPolicy() = with(binding.textViewPrivacyPolicy) {
        movementMethod = LinkMovementMethod.getInstance()
        setTextFromHtml(getString(R.string.registration_privacy_policy_checkbox_text))
        stripUnderlines()
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
}
