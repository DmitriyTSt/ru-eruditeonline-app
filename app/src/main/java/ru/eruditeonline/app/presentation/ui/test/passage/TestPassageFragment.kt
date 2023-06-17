package ru.eruditeonline.app.presentation.ui.test.passage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.core.view.updateMargins
import androidx.core.view.updatePadding
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.test.CompetitionTest
import ru.eruditeonline.app.data.model.test.Question
import ru.eruditeonline.app.databinding.FragmentTestPassageBinding
import ru.eruditeonline.app.presentation.extension.addLinearSpaceItemDecoration
import ru.eruditeonline.app.presentation.extension.appViewModels
import ru.eruditeonline.app.presentation.extension.doOnApplyWindowInsets
import ru.eruditeonline.app.presentation.extension.errorSnackbar
import ru.eruditeonline.app.presentation.extension.hideSoftKeyboard
import ru.eruditeonline.app.presentation.extension.load
import ru.eruditeonline.app.presentation.extension.setTextFromHtml
import ru.eruditeonline.app.presentation.navigation.observeNavigationCommands
import ru.eruditeonline.app.presentation.ui.base.BaseFragment
import java.util.Locale
import javax.inject.Inject

class TestPassageFragment : BaseFragment(R.layout.fragment_test_passage) {
    private val binding by viewBinding(FragmentTestPassageBinding::bind)
    private val viewModel: TestPassageViewModel by appViewModels()
    private val args: TestPassageFragmentArgs by navArgs()

    private val margin16 by lazy { resources.getDimensionPixelSize(R.dimen.margin_16) }

    @Inject lateinit var answersAdapter: AnswersAdapter

    override fun callOperations() {
        viewModel.loadTest(args.id)
    }

    override fun setupLayout(savedInstanceState: Bundle?) = with(binding) {
        stateViewFlipper.setRetryMethod { viewModel.loadTest(args.id) }
        content.linearProgressIndicator.setProgressCompat(0, false)
        setupInsets()
        setupCloseScreen()
        setupAnswers()
    }

    override fun onBindViewModel() = with(viewModel) {
        observeNavigationCommands(viewModel)
        testLiveData.observe { state ->
            binding.apply {
                appBarLayoutLoading.isVisible = !state.isSuccess
                stateViewFlipper.setState(state)
            }
            state.doOnSuccess { test ->
                bindTest(test)
            }
        }
        questionLiveData.observe { (index, question) ->
            bindQuestion(index, question)
        }
        errorRequiredLiveEvent.observe {
            errorSnackbar(getString(R.string.test_passage_required_answer_error), binding.content.fabSelect.height)
        }
    }

    private fun setupInsets() = with(binding) {
        root.doOnApplyWindowInsets { _, insets, _ ->
            val windowInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime())
            toolbarLoading.updatePadding(top = windowInsets.top)
            content.appBarLayout.updatePadding(top = windowInsets.top)
            content.linearLayoutContent.updatePadding(bottom = windowInsets.bottom)
            content.fabSelect.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                updateMargins(bottom = margin16 + windowInsets.bottom)
            }
            WindowInsetsCompat.Builder().setInsets(
                WindowInsetsCompat.Type.systemBars(),
                Insets.of(
                    windowInsets.left,
                    0,
                    windowInsets.right,
                    windowInsets.bottom
                )
            ).build()
        }
    }

    private fun setupCloseScreen() = with(binding) {
        toolbarLoading.setNavigationOnClickListener {
            closeScreen()
        }
        content.toolbar.setNavigationOnClickListener {
            closeScreen()
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner) {
            closeScreen()
        }
    }

    private fun closeScreen() {
        AlertDialog.Builder(binding.root.context)
            .setMessage(R.string.test_passage_close_message)
            .setPositiveButton(R.string.yes) { _, _ ->
                viewModel.navigateBack()
            }
            .setNegativeButton(R.string.no, null)
            .show()
    }

    private fun setupAnswers() = with(binding.content.recyclerView) {
        adapter = answersAdapter
        addLinearSpaceItemDecoration(R.dimen.padding_16)
    }

    private fun bindTest(test: CompetitionTest) = with(binding.content) {
        textViewTitle.text = test.title
        toolbar.title = test.ageCategoryTitle?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
        }
        linearProgressIndicator.max = test.questions.size
    }

    @SuppressLint("SetTextI18n")
    private fun bindQuestion(index: Int, question: Question) = with(binding.content) {
        textViewQuestionText.setTextFromHtml("№ ${index + 1}. ${question.text}")
        cardViewQuestionImage.isVisible = !question.image.isNullOrEmpty()
        if (cardViewQuestionImage.isVisible) {
            imageViewQuestion.load(question.image)
        }
        linearProgressIndicator.setProgressCompat(index + 1, false)
        nestedScrollView.scrollTo(0, 0)

        when (question) {
            is Question.ListAnswer -> {
                textInputLayoutAnswer.isVisible = false
                recyclerView.isVisible = true
                answersAdapter.selectedAnswerId = null
                answersAdapter.submitList(question.answers)
                fabSelect.setOnClickListener {
                    viewModel.saveListAnswer(answersAdapter.selectedAnswerId, question.id)
                }
                activity?.hideSoftKeyboard()
            }
            is Question.SingleAnswer -> {
                textInputLayoutAnswer.isVisible = true
                recyclerView.isVisible = false
                textInputLayoutAnswer.hint = question.label
                editTextAnswer.setText("")
                fabSelect.setOnClickListener {
                    viewModel.saveSingleAnswer(editTextAnswer.text.toString(), question.id)
                }
            }
        }
    }
}
