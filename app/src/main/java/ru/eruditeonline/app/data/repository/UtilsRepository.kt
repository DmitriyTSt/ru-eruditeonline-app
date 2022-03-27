package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.data.model.base.Diploma

interface UtilsRepository {
    /** Получение стран */
    suspend fun getCountries(): List<Country>

    /** Получение видов дипломов */
    suspend fun getDiplomas(): List<Diploma>
}
