package ru.eruditeonline.app.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.eruditeonline.app.presentation.EruditeApplication

@Module
class ApplicationModule {

    @Provides
    fun provideContext(app: EruditeApplication): Context {
        return app.applicationContext
    }

    @Provides
    fun provideApplication(app: EruditeApplication): Application {
        return app
    }
}