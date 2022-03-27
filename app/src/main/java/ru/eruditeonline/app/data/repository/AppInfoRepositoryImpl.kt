package ru.eruditeonline.app.data.repository

import javax.inject.Inject

class AppInfoRepositoryImpl @Inject constructor(
    buildConfigRepository: BuildConfigRepository,
) : AppInfoRepository {
    override val isRelease: Boolean = buildConfigRepository.buildType == AppInfoRepository.RELEASE
    override val isDebug: Boolean = buildConfigRepository.buildType == AppInfoRepository.DEBUG
    override val isInternal: Boolean = buildConfigRepository.buildType == AppInfoRepository.INTERNAL
}