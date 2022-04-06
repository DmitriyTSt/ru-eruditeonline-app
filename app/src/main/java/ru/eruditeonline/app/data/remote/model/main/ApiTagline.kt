package ru.eruditeonline.app.data.remote.model.main

import com.google.gson.annotations.SerializedName

class ApiTagline(
    /** Заголовок */
    @SerializedName("title") val title: String?,
    /** Текст */
    @SerializedName("text") val text: String?,
    /** Ссылка на иконку */
    @SerializedName("icon") val icon: String?,
    /** Цвет заголовка */
    @SerializedName("titleColor") val titleColor: String?,
)
