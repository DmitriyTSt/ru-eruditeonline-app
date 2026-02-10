package ru.eruditeonline.app.domain.repository

interface ComposeThemeRepository {
    suspend fun getTheme(): String?
    suspend fun setTheme(theme: String)
}
