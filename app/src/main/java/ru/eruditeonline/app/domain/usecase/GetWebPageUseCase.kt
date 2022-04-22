package ru.eruditeonline.app.domain.usecase

import ru.eruditeonline.app.data.model.base.WebPage
import ru.eruditeonline.app.data.repository.UtilsRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Получение веб-страницы
 */
class GetWebPageUseCase @Inject constructor(
    private val utilsRepository: UtilsRepository,
) : UseCaseUnary<GetWebPageUseCase.Params, WebPage>() {

    override suspend fun execute(params: Params): WebPage {
        return utilsRepository.getWebPage(params.path).let { it.copy(path = params.path) }
    }

    data class Params(
        val path: String,
    )
}