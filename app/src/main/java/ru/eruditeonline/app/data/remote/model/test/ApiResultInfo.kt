package ru.eruditeonline.app.data.remote.model.test

import com.google.gson.annotations.SerializedName

class ApiResultInfo(
    /** Место (Победитель (2 место)) */
    @SerializedName("placeText") val placeText: String?,
    /** Средний балл по вем участникам в процентах */
    @SerializedName("averageScore") val averageScore: Int?,
    /** Описание результата */
    @SerializedName("resultText") val resultText: String?,
)
