package ru.eruditeonline.app.data.repository

interface AppInfoRepository {
    val isRelease: Boolean
    val isDebug: Boolean
    val isInternal: Boolean

    val userAgent: String

    companion object {
        // Build types
        const val RELEASE = "release"
        const val INTERNAL = "internal"
        const val DEBUG = "debug"
    }
}
