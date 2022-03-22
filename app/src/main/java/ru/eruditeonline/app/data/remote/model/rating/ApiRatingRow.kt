package ru.eruditeonline.app.data.remote.model.rating

import com.google.gson.annotations.SerializedName

class ApiRatingRow(
    /** Место */
    @SerializedName("rank") val rank: Int?,
    /** Участник */
    @SerializedName("username") val username: String?,
    /** Баллы */
    @SerializedName("score") val score: Int?,
    /** Ссылка на иконку страны */
    @SerializedName("countryIcon") val countryIcon: String?,
    /** Прошлое место */
    @SerializedName("oldRank") val oldRank: Int?,
)
