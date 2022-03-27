package ru.eruditeonline.app.data.repository

import javax.inject.Inject

class EndpointRepositoryImpl @Inject constructor(
    private val appInfoRepository: AppInfoRepository,
) : EndpointRepository {

    override fun provideEndpoint(): String {
        return if (appInfoRepository.isRelease) {
            provideProdEndpoint()
        } else {
            provideDevEndpoint()
        }
    }
}