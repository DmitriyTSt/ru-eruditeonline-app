package ru.eruditeonline.app.data.remote.model.base

import com.google.gson.annotations.SerializedName

/**
 * Результат
 */
class ApiScore(
    /** Набранные баллы */
    @SerializedName("current") val current: Int?,
    /** Из скольки */
    @SerializedName("max") val max: Int?,
    /** Цвет */
    @SerializedName("color") val color: Int?,
)
