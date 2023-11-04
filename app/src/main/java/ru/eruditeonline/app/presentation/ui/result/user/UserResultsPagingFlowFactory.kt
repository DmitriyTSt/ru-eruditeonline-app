package ru.eruditeonline.app.presentation.ui.result.user

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.domain.usecase.result.GetUserResultsPageUseCase
import ru.eruditeonline.app.presentation.paging.base.BasePagingFlowFactory

class UserResultsPagingFlowFactory(
    private val getUserResultsUseCase: GetUserResultsPageUseCase,
) : BasePagingFlowFactory<UserResultsPagingFlowFactory.Params, Int, TestUserResultRow>() {

    override fun create(params: Params): Flow<PagingData<TestUserResultRow>> {
        return createPager(
            UserResultsPagingSource(
                query = params.query,
                getUserResultsUseCase = getUserResultsUseCase,
            )
        ).flow
    }

    data class Params(
        val query: String?,
    )
}
