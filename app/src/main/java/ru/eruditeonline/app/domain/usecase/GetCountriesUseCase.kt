package ru.eruditeonline.app.domain.usecase

import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.data.repository.UtilsRepository
import ru.eruditeonline.usecase.UseCaseUnary
import javax.inject.Inject

/**
 * Получение стран
 */
class GetCountriesUseCase @Inject constructor(
    private val utilsRepository: UtilsRepository,
) : UseCaseUnary<Unit, List<Country>> {

    override suspend fun execute(params: Unit): List<Country> {
        return utilsRepository.getCountries()
    }
}
