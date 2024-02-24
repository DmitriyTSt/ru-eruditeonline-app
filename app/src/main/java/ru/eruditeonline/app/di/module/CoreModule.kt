package ru.eruditeonline.app.di.module

import dagger.Binds
import dagger.Module
import ru.eruditeonline.app.data.repository.AppInfoRepositoryImpl
import ru.eruditeonline.core.domain.repository.AppInfoRepository

@Module
abstract class CoreModule {
    @Binds
    abstract fun provideAppInfoRepository(appInfoRepository: AppInfoRepositoryImpl): AppInfoRepository
}
