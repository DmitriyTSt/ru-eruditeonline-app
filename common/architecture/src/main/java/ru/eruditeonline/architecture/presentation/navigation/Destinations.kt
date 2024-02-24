package ru.eruditeonline.architecture.presentation.navigation

import android.content.Intent

sealed class Destination {
    class Activity(val intent: Intent) : Destination()
    class Screen(val screen: AbstractScreenDestination) : Destination()
    class Stack(vararg val destinations: Destination) : Destination()
    data object Back : Destination()

    /** Для удобного добавления экстов */
    companion object
}

/**
 * Какая-то реализация перехода на экран
 * Зависит от UI слоя
 */
interface AbstractScreenDestination

/**
 * Маркерный интерфейс для переходов экранов
 */
interface Destinations
