package ru.eruditeonline.app.presentation.ui.test.passage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.test.CompetitionTest
import ru.eruditeonline.app.data.model.test.Question
import ru.eruditeonline.app.domain.usecase.test.GetTestUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class TestPassageViewModel @Inject constructor(
    private val getTestUseCase: GetTestUseCase,
) : BaseViewModel() {
    /** Тест */
    private val _testLiveData = MutableLiveData<LoadableState<CompetitionTest>>()
    val testLiveData: LiveData<LoadableState<CompetitionTest>> = _testLiveData.map { state ->
        state.doOnSuccess { test ->
            if (test.questions.isNotEmpty()) {
                _questionLiveData.postValue(0 to test.questions.first())
            }
        }
        state
    }

    /** Индекс вопроса и вопрос */
    private val _questionLiveData = MutableLiveData<Pair<Int, Question>>()
    val questionLiveData: LiveData<Pair<Int, Question>> = _questionLiveData

    fun loadTest(id: String) {
        _testLiveData.launchLoadData(getTestUseCase.executeFlow(GetTestUseCase.Params(id)))
    }

    fun next() {
        val questions = testLiveData.value?.getOrNull()?.questions
        if (!questions.isNullOrEmpty()) {
            val lastIndex = questionLiveData.value?.first ?: 0
            val newIndex = lastIndex + 1
            if (newIndex == questions.size) {
                // TODO check answers
            } else {
                _questionLiveData.postValue(newIndex to questions[newIndex])
            }
        }
    }
}
