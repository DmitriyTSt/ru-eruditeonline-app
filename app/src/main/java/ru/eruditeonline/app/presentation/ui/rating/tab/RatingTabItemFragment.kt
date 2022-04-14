package ru.eruditeonline.app.presentation.ui.rating.tab

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.updatePadding
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.datepicker.MaterialDatePicker
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.rating.RatingRow
import ru.eruditeonline.app.databinding.FragmentRatingTabItemBinding
import ru.eruditeonline.app.presentation.extension.addLinearSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.managers.DateFormatter
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import javax.inject.Inject

class RatingTabItemFragment : BaseFragment(R.layout.fragment_rating_tab_item) {

    companion object {
        private const val EXTRA_MODE = "extra_mode"

        fun newInstance(mode: RatingTabItemMode): RatingTabItemFragment {
            return RatingTabItemFragment().apply {
                arguments = bundleOf(EXTRA_MODE to mode.ordinal)
            }
        }
    }

    private val binding by viewBinding(FragmentRatingTabItemBinding::bind)
    private val viewModel: RatingTabItemViewModel by appViewModels()

    private val padding16 by lazy { resources.getDimensionPixelSize(R.dimen.padding_16) }
    private val mode by lazy { RatingTabItemMode.values()[arguments?.getInt(EXTRA_MODE) ?: 0] }

    @Inject lateinit var dateFormatter: DateFormatter
    @Inject lateinit var ratingRowsAdapter: RatingRowsAdapter

    override fun callOperations() {
        viewModel.initLoad(mode)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        stateViewFlipper.setRetryMethod { viewModel.retry(mode) }
        setupList()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        ratingLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
            state.doOnSuccess { rating ->
                bindRating(rating)
            }
        }
        selectedDateLiveData.observe { date ->
            bindPeriod(date)
        }
    }

    override fun applyBottomNavigationViewPadding(view: View, bottomNavigationViewHeight: Int) {
        binding.recyclerView.updatePadding(bottom = bottomNavigationViewHeight + padding16)
    }

    private fun setupList() = with(binding.recyclerView) {
        adapter = ratingRowsAdapter
        addLinearSpaceItemDecoration(R.dimen.padding_8)
    }

    private fun bindPeriod(date: LocalDate) = with(binding) {
        val formattedDate = when (mode) {
            RatingTabItemMode.DAY -> dateFormatter.formatTextMonthFull(date)
            RatingTabItemMode.MONTH -> dateFormatter.formatTextMonthMonth(date)
            RatingTabItemMode.YEAR -> dateFormatter.formatYear(date)
        }
        editText.setText(formattedDate)
        when (mode) {
            RatingTabItemMode.DAY -> setupDayPicker(date)
            RatingTabItemMode.MONTH -> setupMonthPicker(date)
            RatingTabItemMode.YEAR -> setupYearPicker(date)
        }
    }

    private fun setupDayPicker(initialDate: LocalDate) = with(binding) {
        val hint = getString(R.string.rating_day_hint)
        textInputLayout.hint = hint
        viewSelectDate.setOnClickListener {
            MaterialDatePicker.Builder.datePicker()
                .setTitleText(hint)
                .setSelection(initialDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC) * 1000)
                .build()
                .apply {
                    addOnPositiveButtonClickListener { millis ->
                        viewModel.selectDay(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate())
                    }
                }
                .show(childFragmentManager, hint)
        }
    }

    private fun setupMonthPicker(initialDate: LocalDate) = with(binding) {
        textInputLayout.hint = getString(R.string.rating_month_hint)
        viewSelectDate.setOnClickListener {

        }
    }

    private fun setupYearPicker(initialDate: LocalDate) = with(binding) {
        textInputLayout.hint = getString(R.string.rating_year_hint)
        viewSelectDate.setOnClickListener {

        }
    }

    private fun bindRating(rating: List<RatingRow>) {
        ratingRowsAdapter.submitList(rating)
    }
}