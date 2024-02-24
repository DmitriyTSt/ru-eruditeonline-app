package ru.eruditeonline.app.domain.usecase

import ru.eruditeonline.app.data.model.base.WebPageItem
import ru.eruditeonline.app.data.repository.UtilsRepository
import ru.eruditeonline.usecase.UseCaseUnary
import javax.inject.Inject

/**
 * Получение списка веб-страниц
 */
class GetWebPagesUseCase @Inject constructor(
    private val utilsRepository: UtilsRepository,
) : UseCaseUnary<Unit, List<WebPageItem>> {
    override suspend fun execute(params: Unit): List<WebPageItem> {
        return utilsRepository.getWebPages()
    }
}
