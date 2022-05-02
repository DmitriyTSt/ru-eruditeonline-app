package ru.eruditeonline.app.domain.usecase.debug

import ru.eruditeonline.app.data.repository.AppInfoRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Показывать ли кнопку дебаг экрана
 */
class IsDebugButtonVisibleUseCase @Inject constructor(
    private val appInfoRepository: AppInfoRepository,
) : UseCaseUnary<Unit, Boolean>() {

    override suspend fun execute(params: Unit): Boolean {
        return !appInfoRepository.isRelease
    }
}