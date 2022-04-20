package ru.eruditeonline.app.data.repository

import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.data.model.base.Diploma
import ru.eruditeonline.app.data.model.base.WebPage
import ru.eruditeonline.app.data.model.base.WebPageItem

interface UtilsRepository {
    /** Получение стран */
    suspend fun getCountries(): List<Country>

    /** Получение видов дипломов */
    suspend fun getDiplomas(): List<Diploma>

    /** Получить список веб-страниц */
    suspend fun getWebPages(): List<WebPageItem>

    /** Получить веб-страницу */
    suspend fun getWebPage(path: String): WebPage
}
