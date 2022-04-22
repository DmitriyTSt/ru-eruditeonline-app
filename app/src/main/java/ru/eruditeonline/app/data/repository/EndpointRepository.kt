package ru.eruditeonline.app.data.repository

private const val DEV_ENDPOINT = "http://erudite-online-ru-dev.dmitriyt.ru:8080/api/"
private const val PROD_ENDPOINT = "http://localhost:8080/api/"

interface EndpointRepository {
    fun provideEndpoint(): String
    fun provideDevEndpoint() = DEV_ENDPOINT
    fun provideProdEndpoint() = PROD_ENDPOINT
}
