package ru.eruditeonline.app.presentation.ui.result.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.presentation.extension.bindPagingState
import ru.eruditeonline.app.presentation.extension.launchPagingData
import ru.eruditeonline.architecture.presentation.base.BaseViewModel
import ru.eruditeonline.architecture.presentation.model.LoadableState
import javax.inject.Inject

private const val SEARCH_DELAY = 300L

class UserResultListViewModel @Inject constructor(
    private val userResultsPagingFlowFactory: UserResultsPagingFlowFactory,
    private val resultsByEmailPagingFlowFactory: ResultsByEmailPagingFlowFactory,
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
        when (params) {
            UserResultParams.All -> loadUserResults(null)
            is UserResultParams.Email -> loadResultsByEmail(params.email)
        }
    }

    fun search(query: String) {
        if (lastSearchQuery == query) return
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DELAY)
            lastSearchQuery = query
            loadUserResults(query = query)
        }
    }

    fun bindPagingState(loadState: CombinedLoadStates) {
        _pagingStateLiveData.bindPagingState(loadState)
    }

    fun openResult(result: TestUserResultRow) {
        navigate(destinations.result(result.id))
    }

    private fun loadUserResults(query: String?) {
        _resultsLiveData.launchPagingData(viewModelScope) {
            userResultsPagingFlowFactory.create(UserResultsPagingFlowFactory.Params(query))
        }
    }

    private fun loadResultsByEmail(email: String) {
        _resultsLiveData.launchPagingData(viewModelScope) {
            resultsByEmailPagingFlowFactory.create(ResultsByEmailPagingFlowFactory.Params(email))
        }
    }
}
