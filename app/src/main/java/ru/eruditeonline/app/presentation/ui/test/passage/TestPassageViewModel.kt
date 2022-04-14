package ru.eruditeonline.app.presentation.ui.test.passage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.competition.CompetitionPassData
import ru.eruditeonline.app.data.model.test.CompetitionTest
import ru.eruditeonline.app.data.model.test.Question
import ru.eruditeonline.app.domain.usecase.test.GetTestUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import java.time.LocalDateTime
import java.time.ZoneOffset
import javax.inject.Inject

class TestPassageViewModel @Inject constructor(
    private val getTestUseCase: GetTestUseCase,
    private val destinations: TestPassageDestinations,
) : BaseViewModel() {
    /** Тест */
    private val _testLiveData = MutableLiveData<LoadableState<CompetitionTest>>()
    val testLiveData: LiveData<LoadableState<CompetitionTest>> = _testLiveData.map { state ->
        state.doOnSuccess { test ->
            startTestTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
            if (test.questions.isNotEmpty()) {
                _questionLiveData.postValue(0 to test.questions.first())
            }
        }
        state
    }

    /** Индекс вопроса и вопрос */
    private val _questionLiveData = MutableLiveData<Pair<Int, Question>>()
    val questionLiveData: LiveData<Pair<Int, Question>> = _questionLiveData

    private var startTestTime = 0L
    private val questionResults = mutableListOf<CompetitionPassData.Question>()

    fun loadTest(id: String) {
        _testLiveData.launchLoadData(getTestUseCase.executeFlow(GetTestUseCase.Params(id)))
    }

    fun saveSingleAnswer(text: String, questionId: Int) {
        questionResults.add(CompetitionPassData.Question.SingleAnswer(questionId, text.takeIf { it.isNotEmpty() }))
        next()
    }

    fun saveListAnswer(answerId: String?, questionId: Int) {
        questionResults.add(CompetitionPassData.Question.ListAnswer(questionId, answerId))
        next()
    }

    private fun next() {
        val test = testLiveData.value?.getOrNull() ?: return

        val questions = test.questions.takeIf { it.isNotEmpty() } ?: return

        val lastIndex = questionLiveData.value?.first ?: 0
        val newIndex = lastIndex + 1
        if (newIndex == questions.size) {
            val spentTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - startTestTime
            navigate(
                destinations.checkResult(
                    CompetitionPassData(
                        testId = test.id,
                        questionResults = questionResults,
                        spentTime = spentTime,
                    )
                )
            )
        } else {
            _questionLiveData.postValue(newIndex to questions[newIndex])
        }
    }
}
