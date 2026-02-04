package ru.eruditeonline.app.data.remote.model.main

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiMainSectionType {
    /** Слоганы */
    @SerialName("TAGLINE") TAGLINE,

    /** КОнкурсы */
    @SerialName("COMPETITION_ITEM") COMPETITION_ITEM,
}
