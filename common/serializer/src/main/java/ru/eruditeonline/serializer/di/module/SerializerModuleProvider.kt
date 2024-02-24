package ru.eruditeonline.serializer.di.module

import dagger.Module

@Module(
    includes = [
        GsonModule::class,
    ]
)
abstract class SerializerModuleProvider
