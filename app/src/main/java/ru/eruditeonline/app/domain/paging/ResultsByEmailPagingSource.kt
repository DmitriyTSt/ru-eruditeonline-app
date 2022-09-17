package ru.eruditeonline.app.domain.paging

import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.data.repository.ResultRepository
import ru.eruditeonline.app.domain.paging.base.BasePagingSource

class ResultsByEmailPagingSource(
    private val email: String,
    private val repository: ResultRepository,
) : BasePagingSource<TestUserResultRow>() {

    override suspend fun loadPage(offset: Int, limit: Int): List<TestUserResultRow> {
        return repository.getResultsByEmail(email, offset, limit).data?.list.orEmpty()
    }
}
