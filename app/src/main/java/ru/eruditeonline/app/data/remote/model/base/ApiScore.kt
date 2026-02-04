package ru.eruditeonline.app.data.remote.model.base

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Результат
 */
@Serializable
data class ApiScore(
    /** Набранные баллы */
    @SerialName("current") val current: Int? = null,
    /** Из скольки */
    @SerialName("max") val max: Int? = null,
    /** Цвет */
    @SerialName("color") val color: Int? = null,
)
