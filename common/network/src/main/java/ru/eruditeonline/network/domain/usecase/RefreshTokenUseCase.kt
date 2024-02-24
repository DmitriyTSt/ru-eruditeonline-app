package ru.eruditeonline.network.domain.usecase

import ru.eruditeonline.network.domain.model.AccessToken
import ru.eruditeonline.usecase.UseCaseUnary

/**
 * Рефреш токена
 * Возвращает AccessToken
 */
interface RefreshTokenUseCase : UseCaseUnary<Unit, AccessToken>
