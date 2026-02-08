package ru.eruditeonline.app.data.remote.model.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.base.ApiScore

@Serializable
data class ApiTestUserResult(
    /** Идентификатор результата */
    @SerialName("id") val id: Int? = null,
    /** Дата прохождения */
    @SerialName("date") val date: Long? = null,
    /** Участник */
    @SerialName("username") val username: String? = null,
    /** Идентификатор теста */
    @SerialName("testId") val testId: String? = null,
    /** Название конкурса */
    @SerialName("competitionTitle") val competitionTitle: String? = null,
    /** Место */
    @SerialName("place") val place: String? = null,
    /** Балл */
    @SerialName("score") val score: ApiScore? = null,
    /** Затраченное время */
    @SerialName("spentTime") val spentTime: Long? = null,
    /** Ответы */
    @SerialName("answers") val answers: List<ApiResultAnswer>? = null,
)
