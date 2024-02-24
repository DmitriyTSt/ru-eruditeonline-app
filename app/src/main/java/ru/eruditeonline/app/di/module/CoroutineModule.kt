package ru.eruditeonline.app.di.module

import dagger.Module
import dagger.Provides
import ru.eruditeonline.coroutine.DispatcherProvider

@Module
class CoroutineModule {

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return DispatcherProvider()
    }
}
