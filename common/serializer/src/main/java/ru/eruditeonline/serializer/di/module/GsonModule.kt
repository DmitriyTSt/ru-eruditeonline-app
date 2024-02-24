package ru.eruditeonline.serializer.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import ru.eruditeonline.serializer.TypeAdaptersProvider
import javax.inject.Singleton

@Module
class GsonModule {

    @Singleton
    @Provides
    fun provideGson(
        typeAdaptersProvider: TypeAdaptersProvider,
    ): Gson {
        return GsonBuilder().apply {
            typeAdaptersProvider.provideTypeAdapters().forEach { (key, value) ->
                registerTypeAdapter(key, value)
            }
        }.create()
    }
}