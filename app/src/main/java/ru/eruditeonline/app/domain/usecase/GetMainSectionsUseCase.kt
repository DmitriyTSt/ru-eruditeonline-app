package ru.eruditeonline.app.domain.usecase

import ru.eruditeonline.app.data.model.main.MainSection
import ru.eruditeonline.app.data.repository.MainRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Получить блоки главной страницы
 */
class GetMainSectionsUseCase @Inject constructor(
    private val mainRepository: MainRepository,
) : UseCaseUnary<Unit, List<MainSection>>() {
    override suspend fun execute(params: Unit): List<MainSection> {
        return mainRepository.getMainSections()
    }
}