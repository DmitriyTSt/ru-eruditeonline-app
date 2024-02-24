package ru.eruditeonline.app.data.typeadapter

import ru.eruditeonline.serializer.SerializerTypeAdapter
import ru.eruditeonline.serializer.TypeAdaptersProvider
import java.lang.reflect.Type
import javax.inject.Inject

class TypeAdaptersProviderImpl @Inject constructor() : TypeAdaptersProvider {
    override fun provideTypeAdapters(): Map<Type, SerializerTypeAdapter> {
        return mapOf()
    }
}
