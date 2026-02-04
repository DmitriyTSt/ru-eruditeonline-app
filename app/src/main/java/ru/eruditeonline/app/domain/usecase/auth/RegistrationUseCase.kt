package ru.eruditeonline.app.domain.usecase.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.model.auth.Gender
import ru.eruditeonline.app.data.repository.AuthRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

/**
 * Регистрация
 */
class RegistrationUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) : UseCaseUnary<RegistrationUseCase.Params, Unit>() {

    override suspend fun execute(params: Params) {
        authRepository.registration(
            email = params.email,
            password = params.password,
            name = params.name,
            surname = params.surname,
            patronymic = params.patronymic.takeIf { it.isNotBlank() },
            birthday = params.birthday.takeIf { it > 0 },
            gender = params.gender,
            company = params.company.takeIf { it.isNotBlank() },
            city = params.city,
            region = params.region.takeIf { it.isNotBlank() },
            country = params.country,
            emailAgreement = params.emailAgreement,
        )
    }

    @Serializable
    data class Params(
        @SerialName("email") val email: String,
        @SerialName("password") val password: String,
        @SerialName("name") val name: String,
        @SerialName("surname") val surname: String,
        @SerialName("patronymic") val patronymic: String,
        @SerialName("birthday") val birthday: Long,
        @SerialName("gender") val gender: Gender,
        @SerialName("company") val company: String,
        @SerialName("city") val city: String,
        @SerialName("region") val region: String,
        @SerialName("country") val country: String,
        @SerialName("emailAgreement") val emailAgreement: Boolean,
    )
}
