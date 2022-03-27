package ru.eruditeonline.app.data.remote.model.test

import com.google.gson.annotations.SerializedName

enum class ApiQuestionType {
    @SerializedName("LIST_ANSWER") LIST_ANSWER,
    @SerializedName("SINGLE_ANSWER") SINGLE_ANSWER,
}
