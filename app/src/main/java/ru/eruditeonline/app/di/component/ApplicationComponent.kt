package ru.eruditeonline.app.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.eruditeonline.app.di.module.ActivityModule
import ru.eruditeonline.app.di.module.ApplicationModule
import ru.eruditeonline.app.di.module.FragmentModule
import ru.eruditeonline.app.di.module.ViewModelModule
import ru.eruditeonline.app.presentation.EruditeApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        FragmentModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<EruditeApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: EruditeApplication): ApplicationComponent
    }
}
