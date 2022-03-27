package ru.eruditeonline.app.data.repository

interface TokenRepository {
    var accessToken: String?
    var refreshToken: String?
}
