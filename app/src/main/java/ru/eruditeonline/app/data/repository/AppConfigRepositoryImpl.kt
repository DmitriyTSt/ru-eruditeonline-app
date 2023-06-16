package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.mapper.AppConfigMapper
import ru.eruditeonline.app.data.model.base.AppConfig
import ru.eruditeonline.app.data.remote.ApiService
import ru.eruditeonline.app.data.remote.params.GetAppConfigParams
import javax.inject.Inject

class AppConfigRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: AppConfigMapper,
) : AppConfigRepository {
    override suspend fun getAppConfig(appVersion: String): AppConfig {
        return apiService.getAppConfig(GetAppConfigParams(appVersion)).data.let { mapper.fromApiToModel(it) }
    }
}
