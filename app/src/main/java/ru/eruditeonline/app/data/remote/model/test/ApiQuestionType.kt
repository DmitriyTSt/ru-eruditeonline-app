package ru.eruditeonline.app.data.remote.model.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ApiQuestionType {
    @SerialName("LIST_ANSWER") LIST_ANSWER,
    @SerialName("SINGLE_ANSWER") SINGLE_ANSWER,
}
