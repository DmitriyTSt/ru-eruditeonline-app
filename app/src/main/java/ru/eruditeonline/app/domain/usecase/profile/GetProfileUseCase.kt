package ru.eruditeonline.app.domain.usecase.profile

import ru.eruditeonline.app.data.model.profile.Profile
import ru.eruditeonline.app.data.repository.ProfileRepository
import ru.eruditeonline.usecase.UseCaseUnary
import javax.inject.Inject

/**
 * Получение профиля
 */
class GetProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) : UseCaseUnary<Unit, Profile> {

    override suspend fun execute(params: Unit): Profile {
        return profileRepository.getProfile()
    }
}
