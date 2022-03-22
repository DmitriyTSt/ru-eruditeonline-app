package ru.eruditeonline.app.data.remote.params

import com.google.gson.annotations.SerializedName

class RatingParams(
    @SerializedName("day") val day: Int?,
    @SerializedName("month") val month: Int?,
    @SerializedName("year") val year: Int,
)
