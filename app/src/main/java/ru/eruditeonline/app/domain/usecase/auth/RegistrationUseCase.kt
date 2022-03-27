package ru.eruditeonline.app.domain.usecase.auth

import com.google.gson.annotations.SerializedName
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

    data class Params(
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String,
        @SerializedName("name") val name: String,
        @SerializedName("surname") val surname: String,
        @SerializedName("patronymic") val patronymic: String,
        @SerializedName("birthday") val birthday: Long,
        @SerializedName("gender") val gender: Gender,
        @SerializedName("company") val company: String,
        @SerializedName("city") val city: String,
        @SerializedName("region") val region: String,
        @SerializedName("country") val country: String,
        @SerializedName("emailAgreement") val emailAgreement: Boolean,
    )
}
