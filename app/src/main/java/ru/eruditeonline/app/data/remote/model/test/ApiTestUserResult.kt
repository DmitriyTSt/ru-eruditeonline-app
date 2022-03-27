package ru.eruditeonline.app.data.remote.model.test

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.base.ApiScore

class ApiTestUserResult(
    /** Идентификатор результата */
    @SerializedName("id") val id: String?,
    /** Дата прохождения */
    @SerializedName("date") val date: String?,
    /** Участник */
    @SerializedName("username") val username: String?,
    /** Идентификатор теста */
    @SerializedName("testId") val testId: String?,
    /** Название конкурса */
    @SerializedName("competitionTitle") val competitionTitle: String?,
    /** Место */
    @SerializedName("place") val place: String?,
    /** Балл */
    @SerializedName("score") val score: ApiScore?,
    /** Затраченное время */
    @SerializedName("spentTime") val spentTime: Long?,
    /** Ответы */
    @SerializedName("answers") val answers: List<ApiResultAnswer>?,
)
