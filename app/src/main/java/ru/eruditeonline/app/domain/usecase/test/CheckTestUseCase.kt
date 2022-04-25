package ru.eruditeonline.app.domain.usecase.test

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import ru.eruditeonline.app.data.model.competition.CompetitionPassData
import ru.eruditeonline.app.data.model.test.TempResultWithProfile
import ru.eruditeonline.app.data.preferences.PreferencesStorage
import ru.eruditeonline.app.data.repository.TestRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import ru.eruditeonline.app.domain.usecase.profile.GetProfileUseCase
import javax.inject.Inject

/**
 * Проверка теста
 */
class CheckTestUseCase @Inject constructor(
    private val testRepository: TestRepository,
    private val preferencesStorage: PreferencesStorage,
    private val profileUseCase: GetProfileUseCase,
) : UseCaseUnary<CheckTestUseCase.Params, TempResultWithProfile>() {

    override suspend fun execute(params: Params): TempResultWithProfile = coroutineScope {
        val tempResultRequest = async { testRepository.checkTest(params.passData) }
        val profileRequest = async {
            if (preferencesStorage.isSignedIn) {
                profileUseCase.execute(Unit)
            } else {
                null
            }
        }
        TempResultWithProfile(
            tempResult = tempResultRequest.await(),
            profile = profileRequest.await(),
        )
    }

    data class Params(
        val passData: CompetitionPassData,
    )
}
