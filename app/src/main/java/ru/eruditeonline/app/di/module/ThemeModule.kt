package ru.eruditeonline.app.di.module

import dagger.Binds
import dagger.Module
import ru.eruditeonline.app.presentation.managers.ThemeManagerImpl
import ru.eruditeonline.ui.presentation.theme.ThemeManager

@Module
abstract class ThemeModule {
    @Binds
    abstract fun provideThemeManager(themeManager: ThemeManagerImpl): ThemeManager
}
