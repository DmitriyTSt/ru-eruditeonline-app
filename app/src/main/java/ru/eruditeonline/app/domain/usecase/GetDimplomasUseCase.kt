package ru.eruditeonline.app.domain.usecase

import ru.eruditeonline.app.data.model.base.Diploma
import ru.eruditeonline.app.data.repository.UtilsRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Получение типов дипломов
 */
class GetDimplomasUseCase @Inject constructor(
    private val utilsRepository: UtilsRepository,
) : UseCaseUnary<Unit, List<Diploma>>() {

    override suspend fun execute(params: Unit): List<Diploma> {
        return utilsRepository.getDiplomas()
    }
}
