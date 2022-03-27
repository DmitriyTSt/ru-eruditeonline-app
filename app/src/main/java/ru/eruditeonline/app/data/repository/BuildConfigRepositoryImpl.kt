package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.BuildConfig
import javax.inject.Inject

class BuildConfigRepositoryImpl @Inject constructor() : BuildConfigRepository {
    override val buildType: String = BuildConfig.BUILD_TYPE
    override val versionCode: Int = BuildConfig.VERSION_CODE
    override val versionName: String = BuildConfig.VERSION_NAME
}
