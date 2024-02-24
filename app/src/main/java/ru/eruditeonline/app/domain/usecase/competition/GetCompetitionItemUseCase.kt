package ru.eruditeonline.app.domain.usecase.competition

import ru.eruditeonline.app.data.model.competition.CompetitionItem
import ru.eruditeonline.app.data.repository.CompetitionRepository
import ru.eruditeonline.usecase.UseCaseUnary
import javax.inject.Inject

/**
 * Получение конкурса
 */
class GetCompetitionItemUseCase @Inject constructor(
    private val repository: CompetitionRepository,
) : UseCaseUnary<GetCompetitionItemUseCase.Params, CompetitionItem> {

    override suspend fun execute(params: Params): CompetitionItem {
        return repository.getCompetitionItem(params.id)
    }

    data class Params(
        val id: Int,
    )
}
