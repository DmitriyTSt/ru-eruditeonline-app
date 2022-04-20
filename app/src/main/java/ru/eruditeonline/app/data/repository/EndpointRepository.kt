package ru.eruditeonline.app.data.repository

private const val DEV_ENDPOINT = "http://192.168.0.181:8080/api/"
private const val PROD_ENDPOINT = "http://localhost:8080/api/"

interface EndpointRepository {
    fun provideEndpoint(): String
    fun provideDevEndpoint() = DEV_ENDPOINT
    fun provideProdEndpoint() = PROD_ENDPOINT
}
