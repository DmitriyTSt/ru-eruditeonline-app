package ru.eruditeonline.navigation

sealed class Destination {

    /**
     * Навигация на определенный экран (что угодно, в рамках UI слоя)
     */
    class Screen(val screen: AbstractScreenDestination) : Destination()

    /**
     * Открытие стека экранов
     */
    class Stack(vararg val destinations: Destination) : Destination()

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
