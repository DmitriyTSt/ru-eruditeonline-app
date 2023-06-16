package ru.eruditeonline.app.domain.usecase

import ru.eruditeonline.app.data.model.base.AppConfig
import ru.eruditeonline.app.data.repository.AppConfigRepository
import ru.eruditeonline.app.data.repository.AppInfoRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Получение конфигурации приложения
 */
class GetAppConfigUseCase @Inject constructor(
    private val appInfoRepository: AppInfoRepository,
    private val appConfigRepository: AppConfigRepository,
) : UseCaseUnary<Unit, AppConfig>() {

    override suspend fun execute(params: Unit): AppConfig {
        val appVersion = buildString {
            append(appInfoRepository.versionName)
            append("-")
            append(appInfoRepository.buildType)
            append("(")
            append(appInfoRepository.versionCode)
            append(")")
        }
        return appConfigRepository.getAppConfig(appVersion)
    }
}
