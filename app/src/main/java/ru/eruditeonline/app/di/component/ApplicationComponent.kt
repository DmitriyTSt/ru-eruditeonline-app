package ru.eruditeonline.app.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.eruditeonline.app.di.module.ActivityModule
import ru.eruditeonline.app.di.module.ApiServiceModule
import ru.eruditeonline.app.di.module.ApplicationModule
import ru.eruditeonline.app.di.module.CoroutineModule
import ru.eruditeonline.app.di.module.DeepLinkManagerModule
import ru.eruditeonline.app.di.module.FragmentModule
import ru.eruditeonline.app.di.module.RepositoryModule
import ru.eruditeonline.app.di.module.ViewModelAssistedModule
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
        ViewModelAssistedModule::class,
        RepositoryModule::class,
        CoroutineModule::class,
        ApiServiceModule::class,
        DeepLinkManagerModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<EruditeApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: EruditeApplication): ApplicationComponent
    }
}
