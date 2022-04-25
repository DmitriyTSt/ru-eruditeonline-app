package ru.eruditeonline.app.presentation.ui.test.tempresult

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.Insets
import androidx.core.text.buildSpannedString
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.core.view.updatePadding
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.textfield.TextInputLayout
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.data.model.base.Diploma
import ru.eruditeonline.app.data.model.profile.Profile
import ru.eruditeonline.app.data.model.test.TempResult
import ru.eruditeonline.app.databinding.FragmentTestTempResultBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.doOnApplyWindowInsets
import ru.eruditeonline.app.presentation.extension.errorSnackbar
import ru.eruditeonline.app.presentation.extension.getColorCompat
import ru.eruditeonline.app.presentation.extension.link
import ru.eruditeonline.app.presentation.extension.load
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import ru.eruditeonline.app.presentation.ui.country.SelectCountryFragment
import ru.eruditeonline.app.presentation.ui.diploma.SelectDiplomaFragment
import javax.inject.Inject

class TestTempResultFragment : BaseFragment(R.layout.fragment_test_temp_result) {
    private val binding by viewBinding(FragmentTestTempResultBinding::bind)
    private val viewModel: TestTempResultViewModel by appViewModels()
    private val args: TestTempResultFragmentArgs by navArgs()

    @Inject lateinit var answersAdapter: ResultAnswersAdapter

    override fun callOperations() {
        viewModel.checkTest(args.data)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        setupInsets()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        setRequiredFieldHints()
        setupSaveButton()
        setupPrivacyPolicy()
        setupSelectCountry()
        setupSelectDiploma()
        setupAnswers()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        tempResultLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
            state.doOnSuccess { resultWithProfile ->
                bindResult(resultWithProfile.tempResult)
            }
        }
        profileInfoLiveEvent.observe { profile ->
            bindProfile(profile)
        }
        countryLiveData.observe { country ->
            binding.content.editTextCountry.setText(country.name)
        }
        diplomaLiveData.observe { diploma ->
            binding.content.apply {
                editTextDiploma.setText(
                    getString(R.string.diploma_type_template, diploma.type.replace("type", ""))
                )
                imageViewDiploma.isVisible = true
                imageViewDiploma.load(diploma.image)
            }
        }
        scrollToErrorFieldLiveEvent.observe { viewId ->
            scrollToErrorViewById(viewId)
        }
        emailValidationErrorLiveEvent.observe { id ->
            binding.root.findViewById<TextInputLayout?>(id)?.error = getString(R.string.email_validation_error)
        }
        privacyPolicyErrorLiveEvent.observe {
            errorSnackbar(getString(R.string.privacy_policy_error))
        }
        saveResultLiveEvent.observe { state ->
            binding.content.buttonSaveResult.setState(state)
            state.doOnError { error ->
                errorSnackbar(error)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindResult(result: TempResult) = with(binding.content) {
        scoreProgressView.bindScore(result.score)
        textViewScoreValue.text = getString(R.string.score_template, result.score.current, result.score.max)
        textViewScorePercent.text = "${result.score.current * 100 / result.score.max}%"
        val hasResultInfo = result.resultInfo != null
        textViewPlace.isVisible = hasResultInfo
        textViewAverageScore.isVisible = hasResultInfo
        textViewResultText.isVisible = hasResultInfo
        if (result.resultInfo != null) {
            textViewPlace.text = result.resultInfo.placeText
            textViewAverageScore.text = getString(R.string.temp_result_average_score_template, result.resultInfo.averageScore)
            textViewResultText.text = result.resultInfo.resultText
        }
        textViewSpentTime.text = getString(
            R.string.temp_result_spent_time_template,
            "%02d.%02d".format(
                result.spentTime / 60,
                result.spentTime % 60,
            )
        )
        answersAdapter.submitList(result.answers)
    }

    private fun bindProfile(profile: Profile) = with(binding.content) {
        editTextName.setText(profile.name)
        editTextSurname.setText(profile.surname)
        editTextPatronymic.setText(profile.patronymic)
        if (profile.country != null) {
            viewModel.selectCountry(profile.country)
        }
        editTextRegion.setText(profile.region)
        editTextCity.setText(profile.city)
        editTextSchool.setText(profile.school)
        editTextEmail.setText(profile.email)
    }

    private fun setupInsets() = with(binding) {
        root.doOnApplyWindowInsets { _, insets, _ ->
            val windowInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            toolbar.updatePadding(
                top = windowInsets.top,
            )
            root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                updateMargins(bottom = windowInsets.bottom)
            }
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

    private fun setupSelectCountry() = with(binding.content) {
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

    private fun setupSelectDiploma() = with(binding.content) {
        viewSelectDiploma.setOnClickListener {
            viewModel.openSelectDiploma()
        }
        setFragmentResultListener(SelectDiplomaFragment.REQUEST_CODE) { _, bundle ->
            val diploma = bundle.getParcelable<Diploma?>(SelectDiplomaFragment.KEY_DIPLOMA)
            if (diploma != null) {
                viewModel.selectDiploma(diploma)
            }
        }
    }

    private fun setupAnswers() = with(binding.content) {
        recyclerViewAnswers.adapter = answersAdapter
        imageViewAnswersToggle.setOnClickListener {
            recyclerViewAnswers.isVisible = !recyclerViewAnswers.isVisible
            imageViewAnswersToggle.setImageResource(
                if (recyclerViewAnswers.isVisible) {
                    R.drawable.ic_arrow_up
                } else {
                    R.drawable.ic_arrow_down
                }
            )
        }
    }

    private fun setupSaveButton() = with(binding.content) {
        buttonSaveResult.setOnClickListener {
            viewModel.saveResult(
                surname = textInputLayoutSurname,
                name = textInputLayoutName,
                patronymic = textInputLayoutPatronymic,
                school = textInputLayoutSchool,
                position = textInputLayoutPosition,
                teacher = textInputLayoutTeacher,
                country = textInputLayoutCountry,
                city = textInputLayoutCity,
                region = textInputLayoutRegion,
                email = textInputLayoutEmail,
                teacherEmail = textInputLayoutTeacherEmail,
                diploma = textInputLayoutDiploma,
                isPrivacyPolicyChecked = checkboxPrivacyPolicy.isChecked,
                ratingQuality = ratingBarQuality.rating.toInt(),
                ratingDifficulty = ratingBarDifficulty.rating.toInt(),
                ratingInterest = ratingBarInterest.rating.toInt(),
            )
        }
    }

    private fun setupPrivacyPolicy() = with(binding.content.textViewPrivacyPolicy) {
        movementMethod = LinkMovementMethod.getInstance()
        text = buildSpannedString {
            append(getString(R.string.temp_result_privacy_policy_checkbox_text_part_start))
            append(" ")
            link(viewModel::openPersonalData) {
                append(getString(R.string.privacy_policy_checkbox_text_part_link))
            }
            append(" ")
            append(getString(R.string.privacy_policy_checkbox_text_part_end))
        }
    }

    private fun setRequiredFieldHints() = with(binding.content) {
        textInputLayoutSurname.setFieldRequiredHint()
        textInputLayoutName.setFieldRequiredHint()
        textInputLayoutCity.setFieldRequiredHint()
        textInputLayoutCountry.setFieldRequiredHint()
        textInputLayoutEmail.setFieldRequiredHint()
        textInputLayoutDiploma.setFieldRequiredHint()
    }

    private fun scrollToErrorViewById(viewId: Int) = with(binding) {
        val viewWithError = root.findViewById<View>(viewId)
        val viewTop = when (viewId) {
            R.id.textInputLayoutCountry -> binding.content.constraintLayoutCountry.top
            R.id.textInputLayoutDiploma -> binding.content.constraintLayoutDiploma.top
            else -> viewWithError.top
        }
        content.nestedScrollView.scrollTo(0, viewTop)
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
