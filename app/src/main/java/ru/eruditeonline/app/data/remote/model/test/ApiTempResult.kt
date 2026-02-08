package ru.eruditeonline.app.data.remote.model.test

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.eruditeonline.app.data.remote.model.base.ApiScore

@Serializable
data class ApiTempResult(
    /** Временный идентификатор прохождения теста */
    @SerialName("id") val id: Int? = null,
    /** Выбранные ответы */
    @SerialName("answers") val answers: List<ApiResultAnswer>? = null,
    /** Набранные баллы */
    @SerialName("score") val score: ApiScore? = null,
    /** Затраченное время, в секундах */
    @SerialName("spentTime") val spentTime: Long? = null,
    /** Данные результата (null если олимпиада) */
    @SerialName("resultInfo") val resultInfo: ApiResultInfo? = null,
)
