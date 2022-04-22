package ru.eruditeonline.app.data.repository

private const val DEV_ENDPOINT = "http://31.29.173.233:8080/api/"
private const val PROD_ENDPOINT = "http://localhost:8080/api/"

interface EndpointRepository {
    fun provideEndpoint(): String
    fun provideDevEndpoint() = DEV_ENDPOINT
    fun provideProdEndpoint() = PROD_ENDPOINT
}
