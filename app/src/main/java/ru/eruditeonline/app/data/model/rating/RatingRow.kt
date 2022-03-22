package ru.eruditeonline.app.data.model.rating

class RatingRow(
    /** Место */
    val rank: Int,
    /** Участник */
    val username: String,
    /** Баллы */
    val score: Int,
    /** Ссылка на иконку страны */
    val countryIcon: String,
    /** Прошлое место */
    val oldRank: Int?,
)
