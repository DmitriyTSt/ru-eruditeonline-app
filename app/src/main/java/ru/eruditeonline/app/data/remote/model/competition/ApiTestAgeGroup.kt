package ru.eruditeonline.app.data.remote.model.competition

import com.google.gson.annotations.SerializedName

class ApiTestAgeGroup(
    /** Идентификатор теста */
    @SerializedName("id") val id: String?,
    /** Наименование */
    @SerializedName("title") val title: String?,
    /** Цвет фона */
    @SerializedName("color") val color: String?,
    /** Ссылка на иконку */
    @SerializedName("icon") val icon: String?,
)
