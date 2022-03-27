package ru.eruditeonline.app.data.remote.model.main

import com.google.gson.annotations.SerializedName

enum class ApiMainSectionType {
    /** Слоганы */
    @SerializedName("tagline") TAGLINE,

    /** КОнкурсы */
    @SerializedName("events") COMPETITIONS,
}
