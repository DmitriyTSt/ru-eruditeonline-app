package ru.eruditeonline.app.domain.repository

interface DebugRepository {
    suspend fun isComposeEnabled(): Boolean
    suspend fun setComposeEnabled(isEnabled: Boolean)
}
