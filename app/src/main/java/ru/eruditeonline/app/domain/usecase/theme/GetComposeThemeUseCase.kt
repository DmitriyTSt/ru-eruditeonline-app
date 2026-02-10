package ru.eruditeonline.app.domain.usecase.theme

import ru.eruditeonline.app.domain.repository.ComposeThemeRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

class GetComposeThemeUseCase @Inject constructor(
    private val composeThemeRepository: ComposeThemeRepository,
) : UseCaseUnary<Unit, String?>() {

    override suspend fun execute(params: Unit): String? {
        return composeThemeRepository.getTheme()
    }
}
