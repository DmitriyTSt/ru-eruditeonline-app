package ru.eruditeonline.app.domain.usecase.result

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.data.repository.ResultRepository
import ru.eruditeonline.app.domain.paging.UserResultsPagingSource
import ru.eruditeonline.app.domain.paging.base.createPager
import ru.eruditeonline.app.domain.usecase.base.UseCasePaged
import javax.inject.Inject

/**
 * Получение результатов пользователя
 */
class GetUserResultsUseCase @Inject constructor(
    private val resultRepository: ResultRepository,
) : UseCasePaged<GetUserResultsUseCase.Params, TestUserResultRow>() {

    override fun execute(params: Params): Flow<PagingData<TestUserResultRow>> {
        return createPager(
            UserResultsPagingSource(
                email = params.email,
                query = params.query,
                repository = resultRepository,
            )
        ).flow
    }

    data class Params(
        val query: String,
        val email: String,
    )
}
