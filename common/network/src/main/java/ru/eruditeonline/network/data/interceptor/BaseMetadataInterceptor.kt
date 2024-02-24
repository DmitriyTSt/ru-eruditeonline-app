package ru.eruditeonline.network.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import ru.eruditeonline.core.domain.repository.AppInfoRepository
import javax.inject.Inject

private const val HEADER_USER_AGENT = "User-Agent"
private const val HEADER_CONTENT_TYPE = "Content-Type"

class BaseMetadataInterceptor @Inject constructor(
    private val appInfoRepository: AppInfoRepository,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .header(HEADER_USER_AGENT, appInfoRepository.userAgent)
            .header(HEADER_CONTENT_TYPE, "application/json")
            .method(original.method(), original.body())
            .build()
        return chain.proceed(request)
    }
}