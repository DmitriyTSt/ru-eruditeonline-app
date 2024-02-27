package ru.eruditeonline.navigation

sealed class Destination {

    /**
     * Навигация на определенный экран (что угодно, в рамках UI слоя)
     */
    data class Screen(val screen: AbstractScreenDestination) : Destination()

    /**
     * Открытие стека экранов
     */
    data class Stack(val destinations: List<Destination>) : Destination() {
        constructor(vararg destinations: Destination) : this(destinations.toList())
    }

    /**
     * Переход назад
     */
    data object Back : Destination()

    /**
     * Для удобного добавления экстов
     */
    companion object
}

/**
 * Какая-то реализация перехода куда-то
 * Зависит от UI слоя
 */
interface AbstractScreenDestination

/**
 * Маркерный интерфейс для переходов экранов
 */
interface Destinations
