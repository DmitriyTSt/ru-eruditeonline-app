package ru.eruditeonline.app.presentation.ui.result.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.test.TestUserResult
import ru.eruditeonline.app.databinding.FragmentResultDetailBinding
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.fitTopInsetsWithPadding
import ru.eruditeonline.app.presentation.extension.load
import ru.eruditeonline.app.presentation.managers.DateFormatter
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import javax.inject.Inject

class ResultDetailFragment : BaseFragment(R.layout.fragment_result_detail) {
    private val binding by viewBinding(FragmentResultDetailBinding::bind)
    private val viewModel: ResultDetailViewModel by appViewModels()
    private val args: ResultDetailFragmentArgs by navArgs()

    @Inject lateinit var answersAdapter: ResultAnswersAdapter
    @Inject lateinit var dateFormatter: DateFormatter

    override fun callOperations() {
        viewModel.loadResult(args.id)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        toolbar.fitTopInsetsWithPadding()
        toolbar.setNavigationOnClickListener {
            viewModel.navigateBack()
        }
        content.recyclerViewAnswers.adapter = answersAdapter
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        resultLiveData.observe { state ->
            binding.stateViewFlipper.setState(state)
            state.doOnSuccess { result ->
                bindResult(result)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindResult(result: TestUserResult) = with(binding.content) {
        textViewCompetitionTitle.text = result.competitionTitle
        textViewUsernameValue.text = result.username
        textViewScoreValue.text = getString(R.string.score_template, result.score.current, result.score.max)
        textViewSpentTimeValue.text = "%02d:%02d".format(result.spentTime / 60, result.spentTime % 60)
        textViewDateValue.text = dateFormatter.formatStandardDate(result.date)
        textViewIdValue.text = "${result.testId} ${result.id}"

        answersAdapter.submitList(result.answers)
    }
}