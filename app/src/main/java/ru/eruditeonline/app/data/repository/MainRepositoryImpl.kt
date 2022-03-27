package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.mapper.MainMapper
import ru.eruditeonline.app.data.model.main.MainSection
import ru.eruditeonline.app.data.remote.ApiService
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mainMapper: MainMapper,
) : MainRepository {
    override suspend fun getMainSections(): List<MainSection> {
        return apiService.getMainSections().data?.list.orEmpty().mapNotNull { mainMapper.fromApiToModel(it) }
    }
}
