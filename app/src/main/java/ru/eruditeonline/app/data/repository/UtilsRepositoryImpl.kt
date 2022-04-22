package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.mapper.BaseMapper
import ru.eruditeonline.app.data.mapper.WebPageMapper
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.data.model.base.Diploma
import ru.eruditeonline.app.data.model.base.WebPage
import ru.eruditeonline.app.data.model.base.WebPageItem
import ru.eruditeonline.app.data.remote.ApiService
import javax.inject.Inject

class UtilsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val baseMapper: BaseMapper,
    private val webPageMapper: WebPageMapper,
) : UtilsRepository {
    override suspend fun getCountries(): List<Country> {
        return apiService.getCountries()
            .data
            ?.list
            .orEmpty()
            .map { baseMapper.fromApiToModel(it) }
    }

    override suspend fun getDiplomas(): List<Diploma> {
        return apiService.getDiplomas()
            .data
            ?.list
            .orEmpty()
            .map { baseMapper.fromApiToModel(it) }
    }

    override suspend fun getWebPages(): List<WebPageItem> {
        return apiService.getWebPages()
            .data
            ?.list
            .orEmpty()
            .map { webPageMapper.fromApiToModel(it) }
    }

    override suspend fun getWebPage(path: String): WebPage {
        return apiService.getWebPage(path)
            .data
            .page
            ?.let { webPageMapper.fromApiToModel(it) }
            ?: throw IllegalStateException("page null from api")
    }
}
