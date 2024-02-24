package ru.eruditeonline.network.data

interface ApiClientProvider {
    fun provideRetrofit()
    fun provideRefreshTokenRetrofit()
}