package ru.eruditeonline.app.di.module

import dagger.Binds
import dagger.Module
import ru.eruditeonline.app.presentation.managers.DeepLinkManager
import ru.eruditeonline.app.presentation.managers.InnerDeepLinkManager

@Module
abstract class DeepLinkManagerModule {
    @Binds
    abstract fun provideInnerDeepLinkManager(deepLinkManager: DeepLinkManager): InnerDeepLinkManager
}