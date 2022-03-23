package ru.eruditeonline.app.data.remote.model.auth

import com.google.gson.annotations.SerializedName

enum class ApiGender {
    @SerializedName("NOT_SET") NOT_SET,
    @SerializedName("MALE") MALE,
    @SerializedName("FEMALE") FEMALE,
}
