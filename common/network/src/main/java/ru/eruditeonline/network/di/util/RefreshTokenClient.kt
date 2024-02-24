package ru.eruditeonline.network.di.util

import javax.inject.Qualifier

/**
 * В authenticator OkHttpClient нужно рефрешнуть токен. Для этого нужен репозиторий, который вызовет запрос.
 * Но если используется один OkHttpClient, получим циклическую зависимость.
 * Так что создаем дополнительный OkHttpClient, apiService и репозиторий
 */
@Qualifier
annotation class RefreshTokenClient