package ru.eruditeonline.serializer.di

import ru.eruditeonline.serializer.TypeAdaptersProvider

interface SerializerDeps {
    fun typeAdaptersProvider(): TypeAdaptersProvider
}