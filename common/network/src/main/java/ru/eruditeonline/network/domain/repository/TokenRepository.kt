package ru.eruditeonline.network.domain.repository

import ru.eruditeonline.network.domain.model.AccessToken
import ru.eruditeonline.network.domain.model.RefreshToken

interface TokenRepository {
    var accessToken: AccessToken?
    var refreshToken: RefreshToken?
}
