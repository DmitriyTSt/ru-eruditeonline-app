package ru.eruditeonline.app.presentation.ui.result.user

import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.domain.usecase.result.GetResultsByEmailPageUseCase
import ru.eruditeonline.app.presentation.paging.base.BasePagingSource

class ResultsByEmailPagingSource(
    private val email: String,
    private val getResultsByEmailPageUseCase: GetResultsByEmailPageUseCase,
) : BasePagingSource<TestUserResultRow>() {

    override suspend fun loadPage(offset: Int, limit: Int): List<TestUserResultRow> {
        return getResultsByEmailPageUseCase.execute(GetResultsByEmailPageUseCase.Params(email, offset, limit))
    }
}
