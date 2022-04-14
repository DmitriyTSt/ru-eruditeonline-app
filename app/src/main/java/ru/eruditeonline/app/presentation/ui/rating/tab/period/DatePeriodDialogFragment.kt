package ru.eruditeonline.app.presentation.ui.rating.tab.period

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.FragmentDatePeriodBinding
import ru.eruditeonline.app.presentation.managers.DateFormatter
import ru.eruditeonline.app.presentation.ui.rating.tab.RatingTabItemMode
import java.time.LocalDate

class DatePeriodDialogFragment : DialogFragment(R.layout.fragment_date_period) {

    companion object {
        private const val EXTRA_MODE = "extra_mode"

        fun newInstance(mode: RatingTabItemMode): DatePeriodDialogFragment {
            return DatePeriodDialogFragment().apply {
                arguments = bundleOf(EXTRA_MODE to mode.ordinal)
            }
        }
    }

    private val binding by viewBinding(FragmentDatePeriodBinding::bind)

    private val mode by lazy { RatingTabItemMode.values()[arguments?.getInt(EXTRA_MODE) ?: 1] }
    private val periodsAdapter = DatePeriodsAdapter()
    private val dateFormatter = DateFormatter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = when (mode) {
            RatingTabItemMode.DAY -> ""
            RatingTabItemMode.MONTH -> getString(R.string.rating_month_hint)
            RatingTabItemMode.YEAR -> getString(R.string.rating_year_hint)
        }
        setupList()
        when (mode) {
            RatingTabItemMode.DAY -> Unit
            RatingTabItemMode.MONTH -> bindMonths()
            RatingTabItemMode.YEAR -> bindYear()
        }
    }

    private fun setupList() = with(binding.recyclerView) {
        periodsAdapter.onItemClick = { period ->
            (parentFragment as? DatePeriodListener)?.selectDate(period.date)
            dismiss()
        }
        adapter = periodsAdapter
    }

    private fun bindMonths() {
        periodsAdapter.submitList(
            IntRange(0, 11)
                .map { LocalDate.now().minusMonths(it.toLong()) }
                .map { DatePeriod(it, dateFormatter.formatTextMonthMonth(it)) }
        )
    }

    private fun bindYear() {
        periodsAdapter.submitList(
            IntRange(1, 2)
                .map { LocalDate.now().minusYears(it.toLong()) }
                .map { DatePeriod(it, dateFormatter.formatYear(it)) }
        )
    }
}
