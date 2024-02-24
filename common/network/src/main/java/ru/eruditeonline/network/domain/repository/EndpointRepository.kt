package ru.eruditeonline.network.domain.repository

private const val DEV_ENDPOINT = "http://erudite-online-ru-dev.dmitriyt.ru:8080/api/"
private const val PROD_ENDPOINT = "http://erudit-online.ru:8082/api/"

interface EndpointRepository {
    fun provideEndpoint(): String
    fun updateEndpoint(newEndpoint: String)
    fun provideDevEndpoint() = DEV_ENDPOINT
    fun provideProdEndpoint() = PROD_ENDPOINT
}