package ru.eruditeonline.network.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.eruditeonline.network.domain.repository.TokenRepository
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val tokenRepository: TokenRepository,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .apply {
                val token = tokenRepository.accessToken
                if (token != null) {
                    header(HEADER_AUTHORIZATION, token?.value)
                }
            }
            .method(original.method(), original.body())
            .build()
        return chain.proceed(request)
    }

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
    }
}