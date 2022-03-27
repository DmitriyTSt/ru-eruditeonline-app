package ru.eruditeonline.app.domain.paging

import ru.eruditeonline.app.data.model.test.TestCommonResultRow
import ru.eruditeonline.app.data.repository.ResultRepository
import ru.eruditeonline.app.domain.paging.base.BasePagingSource

class CommonResultsPagingSource(
    private val repository: ResultRepository,
) : BasePagingSource<TestCommonResultRow>() {

    override suspend fun loadPage(offset: Int, limit: Int): List<TestCommonResultRow> {
        return repository.getCommonResults(offset, limit).data?.list.orEmpty()
    }
}
