package ru.eruditeonline.app.domain.usecase.theme

import ru.eruditeonline.app.domain.repository.ComposeThemeRepository
import ru.eruditeonline.app.domain.usecase.base.UseCaseUnary
import javax.inject.Inject

class SetComposeThemeUseCase @Inject constructor(
    private val composeThemeRepository: ComposeThemeRepository,
) : UseCaseUnary<SetComposeThemeUseCase.Params, Unit>() {

    override suspend fun execute(params: Params) {
        composeThemeRepository.setTheme(params.theme)
    }

    data class Params(
        val theme: String,
    )
}
