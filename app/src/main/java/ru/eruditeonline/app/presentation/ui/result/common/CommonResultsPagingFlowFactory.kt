package ru.eruditeonline.app.presentation.ui.result.common

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.domain.usecase.result.GetCommonResultsPageUseCase
import ru.eruditeonline.app.presentation.paging.base.BasePagingFlowFactory

class CommonResultsPagingFlowFactory(
    private val getCommonResultsPageUseCase: GetCommonResultsPageUseCase,
) : BasePagingFlowFactory<Unit, Int, TestCommonResultRow>() {

    override fun create(params: Unit): Flow<PagingData<TestCommonResultRow>> {
        return createPager(
            pagingSource = CommonResultsPagingSource(getCommonResultsPageUseCase),
        ).flow
    }
}
