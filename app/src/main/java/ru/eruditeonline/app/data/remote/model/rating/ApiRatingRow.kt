package ru.eruditeonline.app.data.remote.model.rating

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiRatingRow(
    /** Место */
    @SerialName("rank") val rank: Int? = null,
    /** Участник */
    @SerialName("username") val username: String? = null,
    /** Баллы */
    @SerialName("score") val score: Int? = null,
    /** Ссылка на иконку страны */
    @SerialName("countryIcon") val countryIcon: String? = null,
    /** Прошлое место */
    @SerialName("oldRank") val oldRank: Int? = null,
)
