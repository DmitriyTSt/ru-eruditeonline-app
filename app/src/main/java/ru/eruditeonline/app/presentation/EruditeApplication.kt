package ru.eruditeonline.app.presentation

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.eruditeonline.app.di.component.ApplicationComponent
import ru.eruditeonline.app.di.component.DaggerApplicationComponent
import ru.eruditeonline.app.presentation.managers.ErrorTextInitializer
import javax.inject.Inject

class EruditeApplication : DaggerApplication() {

    private lateinit var appComponent: ApplicationComponent

    @Inject lateinit var errorTextInitializer: ErrorTextInitializer

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerApplicationComponent
            .factory()
            .create(this)
        return appComponent
    }

    override fun onCreate() {
        super.onCreate()
        errorTextInitializer.setDefaultErrorMessage()
    }
}
