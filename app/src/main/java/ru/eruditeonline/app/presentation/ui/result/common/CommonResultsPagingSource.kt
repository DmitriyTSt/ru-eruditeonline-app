package ru.eruditeonline.app.presentation.ui.result.common

import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.domain.usecase.result.GetCommonResultsPageUseCase
import ru.eruditeonline.app.presentation.paging.base.BasePagingSource

class CommonResultsPagingSource(
    private val getCommonResultsPageUseCase: GetCommonResultsPageUseCase,
) : BasePagingSource<TestCommonResultRow>() {

    override suspend fun loadPage(offset: Int, limit: Int): List<TestCommonResultRow> {
        return getCommonResultsPageUseCase.execute(GetCommonResultsPageUseCase.Params(offset, limit))
    }
}
