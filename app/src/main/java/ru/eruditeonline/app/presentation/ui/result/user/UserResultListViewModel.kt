package ru.eruditeonline.app.presentation.ui.result.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.domain.usecase.result.GetUserResultsUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

private const val SEARCH_DELAY = 300L

class UserResultListViewModel @Inject constructor(
    private val getUserResultsUseCase: GetUserResultsUseCase,
    private val destinations: UserResultListDestinations,
) : BaseViewModel() {
    /** Пагинация результатов */
    private val _resultsLiveData = MutableLiveData<PagingData<TestUserResultRow>>()
    val resultsLiveData: LiveData<PagingData<TestUserResultRow>> = _resultsLiveData

    /** Состояние загрузки пагинации */
    private val _pagingStateLiveData = MutableLiveData<LoadableState<Unit>>()
    val pagingStateLiveData: LiveData<LoadableState<Unit>> = _pagingStateLiveData

    private var lastSearchQuery: String = ""
    private var searchJob: Job? = null

    fun init(params: UserResultParams) {
        loadResults(email = (params as? UserResultParams.Email)?.email, query = null)
    }

    fun search(query: String) {
        if (lastSearchQuery == query) return
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DELAY)
            lastSearchQuery = query
            loadResults(email = null, query = query)
        }
    }

    fun bindPagingState(loadState: CombinedLoadStates) {
        _pagingStateLiveData.bindPagingState(loadState)
    }

    fun openResult(result: TestUserResultRow) {
        navigate(destinations.result(result.id))
    }

    private fun loadResults(email: String?, query: String?) {
        _resultsLiveData.launchPagingData {
            getUserResultsUseCase.executeFlow(
                GetUserResultsUseCase.Params(
                    query = query,
                    email = email,
                )
            )
        }
    }
}
