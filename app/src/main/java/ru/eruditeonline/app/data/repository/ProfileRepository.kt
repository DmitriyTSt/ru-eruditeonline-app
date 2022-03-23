package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.model.profile.Profile

interface ProfileRepository {
    /** Получение профиля */
    suspend fun getProfile(): Profile
}
