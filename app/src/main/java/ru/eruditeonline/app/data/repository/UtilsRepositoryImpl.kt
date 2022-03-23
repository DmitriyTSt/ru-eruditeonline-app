package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.mapper.BaseMapper
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.data.model.base.Diploma
import ru.eruditeonline.app.data.remote.ApiService
import javax.inject.Inject

class UtilsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val baseMapper: BaseMapper,
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
}
