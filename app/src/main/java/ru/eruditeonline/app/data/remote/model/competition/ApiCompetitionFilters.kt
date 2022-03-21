package ru.eruditeonline.app.data.remote.model.competition

import com.google.gson.annotations.SerializedName

class ApiCompetitionFilters(
    /** Список фильтрации по возрасту */
    @SerializedName("ages") val ages: List<ApiFilterItem?>?,
    /** Список фильтрации по предметам */
    @SerializedName("subjects") val subjects: List<ApiFilterItem?>?,
)
