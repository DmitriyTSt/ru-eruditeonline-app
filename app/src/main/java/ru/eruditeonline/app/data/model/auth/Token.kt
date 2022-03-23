package ru.eruditeonline.app.data.model.auth

class Token(
    val accessToken: String,
    val refreshToken: String,
    val expiresIn: Long,
)
