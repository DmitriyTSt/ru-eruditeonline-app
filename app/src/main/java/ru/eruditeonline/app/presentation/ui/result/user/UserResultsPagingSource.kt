package ru.eruditeonline.app.presentation.ui.result.user

import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.domain.usecase.result.GetUserResultsPageUseCase
import ru.eruditeonline.app.presentation.paging.base.BasePagingSource

class UserResultsPagingSource(
    private val query: String?,
    private val getUserResultsUseCase: GetUserResultsPageUseCase,
) : BasePagingSource<TestUserResultRow>() {

    override suspend fun loadPage(offset: Int, limit: Int): List<TestUserResultRow> {
        return getUserResultsUseCase.execute(GetUserResultsPageUseCase.Params(query, offset, limit))
    }
}
