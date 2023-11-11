package ru.eruditeonline.app.presentation.ui.profile

import ru.eruditeonline.app.presentation.navigation.Destination

interface CommonProfileDestinations {
    /** Результаты пользователя */
    fun userResults(): Destination

    /** Итоги */
    fun commonResults(): Destination

    /** Поиск результатов по email */
    fun searchResultsByEmail(): Destination

    /** Перезагрузка стека */
    fun reloadStack(): Destination

    /** Информация */
    fun information(): Destination

    /** Настройки */
    fun settings(): Destination
}