package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.model.base.AppConfig

interface AppConfigRepository {
    suspend fun getAppConfig(appVersion: String): AppConfig
}
