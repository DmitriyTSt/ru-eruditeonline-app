package ru.eruditeonline.app.domain.usecase.result

import ru.eruditeonline.app.data.model.test.CreatedResult
import ru.eruditeonline.app.data.repository.ResultRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Сохранение результата
 */
class SaveResultUseCase @Inject constructor(
    private val resultRepository: ResultRepository,
) : UseCaseUnary<SaveResultUseCase.Params, CreatedResult>() {

    override suspend fun execute(params: Params): CreatedResult = with(params) {
        return resultRepository.saveResult(
            completeId = completeId,
            name = name,
            surname = surname,
            patronymic = patronymic.takeIf { it.isNotBlank() },
            school = school.takeIf { it.isNotBlank() },
            position = position.takeIf { it.isNotBlank() },
            teacher = teacher.takeIf { it.isNotBlank() },
            country = country,
            city = city,
            region = region.takeIf { it.isNotBlank() },
            email = email,
            teacherEmail = teacherEmail.takeIf { it.isNotBlank() },
            diplomaType = diplomaType,
            quality = quality.takeIf { it in 1..5 },
            difficulty = difficulty.takeIf { it in 1..5 },
            interest = interest.takeIf { it in 1..5 },
        )
    }

    data class Params(
        val completeId: Long,
        val name: String,
        val surname: String,
        val patronymic: String,
        val school: String,
        val position: String,
        val teacher: String,
        val country: String,
        val city: String,
        val region: String,
        val email: String,
        val teacherEmail: String,
        val diplomaType: String,
        val quality: Int,
        val difficulty: Int,
        val interest: Int,
    )
}
