package ru.eruditeonline.app.data.remote.model.main

import com.google.gson.annotations.SerializedName

enum class ApiCompetitionViewType {
    @SerializedName("row") ROW,
    @SerializedName("card") CARD,
}
