package ru.eruditeonline.app.domain.usecase.result

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.data.repository.ResultRepository
import ru.eruditeonline.app.domain.paging.CommonResultsPagingSource
import ru.eruditeonline.app.domain.paging.base.createPager
import ru.eruditeonline.app.domain.usecase.base.UseCasePaged
import javax.inject.Inject

/**
 * Получение общих результатов
 */
class GetCommonResultsUseCase @Inject constructor(
    private val resultRepository: ResultRepository,
) : UseCasePaged<Unit, TestCommonResultRow>() {

    override fun execute(params: Unit): Flow<PagingData<TestCommonResultRow>> {
        return createPager(
            CommonResultsPagingSource(
                repository = resultRepository,
            )
        ).flow
    }
}
