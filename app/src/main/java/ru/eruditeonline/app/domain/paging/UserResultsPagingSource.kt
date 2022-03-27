package ru.eruditeonline.app.domain.paging

import ru.eruditeonline.app.data.model.test.TestUserResultRow
import ru.eruditeonline.app.data.repository.ResultRepository
import ru.eruditeonline.app.domain.paging.base.BasePagingSource

class UserResultsPagingSource(
    private val email: String?,
    private val query: String?,
    private val repository: ResultRepository,
) : BasePagingSource<TestUserResultRow>() {

    override suspend fun loadPage(offset: Int, limit: Int): List<TestUserResultRow> {
        return repository.getUserResults(email, query, offset, limit).data?.list.orEmpty()
    }
}
