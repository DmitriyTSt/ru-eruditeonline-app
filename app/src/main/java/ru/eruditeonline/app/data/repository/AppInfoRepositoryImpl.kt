package ru.eruditeonline.app.data.repository

import ru.eruditeonline.core.domain.repository.AppInfoRepository
import javax.inject.Inject

class AppInfoRepositoryImpl @Inject constructor(
    buildConfigRepository: BuildConfigRepository,
) : AppInfoRepository {
    override val buildType: String = buildConfigRepository.buildType
    override val isRelease: Boolean = buildConfigRepository.buildType == AppInfoRepository.RELEASE
    override val isDebug: Boolean = buildConfigRepository.buildType == AppInfoRepository.DEBUG
    override val isInternal: Boolean = buildConfigRepository.buildType == AppInfoRepository.INTERNAL

    override val versionCode: Int = buildConfigRepository.versionCode
    override val versionName: String = buildConfigRepository.versionName
    override val versionNameWithSuffix: String =
        "${buildConfigRepository.versionName}${if (isRelease) "" else "-${buildConfigRepository.buildType}"}"

    override val userAgent: String = "android-$versionNameWithSuffix($versionCode)"
}
