package ru.eruditeonline.navigation

import kotlinx.coroutines.flow.SharedFlow

/**
 * Контроллер навигации
 */
interface NavigationController {

    /** Команды навигации */
    val destinationFlow: SharedFlow<Destination>

    /**
     * Навигация на [destination]
     */
    fun navigate(destination: Destination)

    /**
     * Навигация назад
     */
    fun navigateBack()
}