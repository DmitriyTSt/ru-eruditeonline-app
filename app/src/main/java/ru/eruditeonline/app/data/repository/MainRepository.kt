package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.model.main.MainSection

interface MainRepository {
    /** Блоки главной */
    suspend fun getMainSections(): List<MainSection>
}
