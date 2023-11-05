package ru.eruditeonline.app.domain.usecase.debug

import ru.eruditeonline.app.domain.repository.DebugRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Изменение использования композа
 *
 * Возвращает информацию, был ли изменено состояние
 */
class SetComposeEnabledUseCase @Inject constructor(
    private val debugRepository: DebugRepository,
) : UseCaseUnary<SetComposeEnabledUseCase.Params, Boolean>() {

    override suspend fun execute(params: Params): Boolean {
        if (params.isComposeEnabled != debugRepository.isComposeEnabled()) {
            debugRepository.setComposeEnabled(params.isComposeEnabled)
            return true
        }
        return false
    }

    data class Params(
        val isComposeEnabled: Boolean,
    )

}