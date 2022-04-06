package ru.eruditeonline.app.data.remote.model.main

import com.google.gson.annotations.SerializedName

enum class ApiMainSectionType {
    /** Слоганы */
    @SerializedName("TAGLINE") TAGLINE,

    /** КОнкурсы */
    @SerializedName("COMPETITION_ITEM") COMPETITION_ITEM,
}
