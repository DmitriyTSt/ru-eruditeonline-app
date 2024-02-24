package ru.eruditeonline.network.domain.model

/**
 * Ошибка токена, рефрешнуть токен невозможно
 */
class WrongRefreshTokenException(throwable: Throwable?) : Exception(throwable)