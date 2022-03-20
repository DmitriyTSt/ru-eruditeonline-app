package ru.eruditeonline.app.presentation

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.eruditeonline.app.di.component.ApplicationComponent
import ru.eruditeonline.app.di.component.DaggerApplicationComponent

class EruditeApplication : DaggerApplication() {

    private lateinit var appComponent: ApplicationComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerApplicationComponent
            .factory()
            .create(this)
        return appComponent
    }
}