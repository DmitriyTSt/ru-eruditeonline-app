package ru.eruditeonline.app.data.repository

interface BuildConfigRepository {
    val buildType: String
    val versionCode: Int
    val versionName: String
}
