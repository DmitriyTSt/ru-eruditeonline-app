package ru.eruditeonline.app.data.mapper

import ru.eruditeonline.app.data.model.base.AppConfig
import ru.eruditeonline.app.data.model.base.AppUpdate
import ru.eruditeonline.app.data.remote.model.base.ApiAppConfig
import ru.eruditeonline.app.data.remote.model.base.ApiAppUpdate
import ru.eruditeonline.core.domain.ext.orDefault
import javax.inject.Inject

class AppConfigMapper @Inject constructor() {

    fun fromApiToModel(api: ApiAppConfig): AppConfig {
        return AppConfig(
            appUpdate = api.appUpdate?.let { fromApiToModel(it) },
        )
    }

    private fun fromApiToModel(api: ApiAppUpdate): AppUpdate {
        return AppUpdate(
            forceUpdate = api.forceUpdate.orDefault(),
        )
    }
}
