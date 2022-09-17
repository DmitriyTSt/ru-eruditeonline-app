package ru.eruditeonline.app.domain.usecase.result

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.data.repository.ResultRepository
import ru.eruditeonline.app.domain.paging.ResultsByEmailPagingSource
import ru.eruditeonline.app.domain.paging.base.createPager
import ru.eruditeonline.app.domain.usecase.base.UseCasePaged
import javax.inject.Inject

/**
 * Получение результатов по email
 */
class GetResultsByEmailUseCase @Inject constructor(
    private val resultRepository: ResultRepository,
) : UseCasePaged<GetResultsByEmailUseCase.Params, TestUserResultRow>() {

    override fun execute(params: Params): Flow<PagingData<TestUserResultRow>> {
        return createPager(
            ResultsByEmailPagingSource(
                email = params.email,
                repository = resultRepository,
            )
        ).flow
    }

    data class Params(
        val email: String,
    )
}
