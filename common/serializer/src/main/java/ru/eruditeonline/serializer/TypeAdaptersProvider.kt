package ru.eruditeonline.serializer

import java.lang.reflect.Type

/**
 * Провайдер тайп адаптеров для gson с других модулей приложения
 */
interface TypeAdaptersProvider {
    /**
     * Возвращает мапу класс и тайп адаптер
     *
     * Пример
     * Date::class.java to dateTypeAdapter
     */
    fun provideTypeAdapters(): Map<Type, SerializerTypeAdapter>
}