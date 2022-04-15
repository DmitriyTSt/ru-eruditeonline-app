package ru.eruditeonline.app.presentation.ui.result.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.domain.usecase.result.GetUserResultsUseCase
import ru.eruditeonline.app.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class UserResultListViewModel @Inject constructor(
    private val getUserResultsUseCase: GetUserResultsUseCase,
) : BaseViewModel() {
    /** Пагинация результатов */
    private val _resultsLiveData = MutableLiveData<PagingData<TestUserResultRow>>()
    val resultsLiveData: LiveData<PagingData<TestUserResultRow>> = _resultsLiveData

    /** Состояние загрузки пагинации */
    private val _pagingStateLiveData = MutableLiveData<LoadableState<Unit>>()
    val pagingStateLiveData: LiveData<LoadableState<Unit>> = _pagingStateLiveData

    fun load(params: UserResultParams) {
        _resultsLiveData.launchPagingData {
            getUserResultsUseCase.executeFlow(
                GetUserResultsUseCase.Params(
                    query = (params as? UserResultParams.Query)?.query,
                    email = (params as? UserResultParams.Email)?.email,
                )
            )
        }
    }

    fun bindPagingState(loadState: CombinedLoadStates) {
        _pagingStateLiveData.bindPagingState(loadState)
    }

    fun openResult(result: TestUserResultRow) {
        // TODO
    }
}
