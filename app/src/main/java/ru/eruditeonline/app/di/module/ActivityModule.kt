package ru.eruditeonline.app.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.eruditeonline.app.presentation.ui.mainactivity.MainActivity
import ru.eruditeonline.app.presentation.ui.startactivity.DeepLinkStartActivity
import ru.eruditeonline.app.presentation.ui.startactivity.StartActivity

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
    
    @ContributesAndroidInjector
    abstract fun startActivity(): StartActivity

    @ContributesAndroidInjector
    abstract fun deepLinkStartActivity(): DeepLinkStartActivity
}
