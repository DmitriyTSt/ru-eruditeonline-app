package ru.eruditeonline.app.presentation.ui.result.user

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.domain.usecase.result.GetResultsByEmailPageUseCase
import ru.eruditeonline.app.presentation.paging.base.BasePagingFlowFactory
import javax.inject.Inject

class ResultsByEmailPagingFlowFactory @Inject constructor(
    private val getResultsByEmailPageUseCase: GetResultsByEmailPageUseCase,
) : BasePagingFlowFactory<ResultsByEmailPagingFlowFactory.Params, Int, TestUserResultRow>() {

    override fun create(params: Params): Flow<PagingData<TestUserResultRow>> {
        return createPager(
            ResultsByEmailPagingSource(
                email = params.email,
                getResultsByEmailPageUseCase = getResultsByEmailPageUseCase,
            )
        ).flow
    }

    data class Params(
        val email: String,
    )
}
