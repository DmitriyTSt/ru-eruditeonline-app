package ru.eruditeonline.network.di.module

import dagger.Binds
import dagger.Module
import ru.eruditeonline.network.data.repository.EndpointRepositoryImpl
import ru.eruditeonline.network.domain.repository.EndpointRepository

@Module(
    includes = [
        ApiServiceModule::class,
    ]
)
abstract class NetworkModuleProvider {

    @Binds
    abstract fun provideEndpointRepository(endpointRepository: EndpointRepositoryImpl): EndpointRepository
}