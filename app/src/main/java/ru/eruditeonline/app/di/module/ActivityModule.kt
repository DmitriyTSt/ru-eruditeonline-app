package ru.eruditeonline.app.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.eruditeonline.app.presentation.ui.mainactivity.MainActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}
