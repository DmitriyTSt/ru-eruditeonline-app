package ru.eruditeonline.app.di.module

import dagger.Binds
import dagger.Module
import ru.eruditeonline.app.data.typeadapter.TypeAdaptersProviderImpl
import ru.eruditeonline.serializer.TypeAdaptersProvider
import ru.eruditeonline.serializer.di.module.SerializerModuleProvider

@Module
abstract class SerializerModule : SerializerModuleProvider() {

    @Binds
    abstract fun provideTypeAdaptersProvider(typeAdaptersProvider: TypeAdaptersProviderImpl): TypeAdaptersProvider
}
