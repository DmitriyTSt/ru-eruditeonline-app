package ru.eruditeonline.app.data.remote.model.test

import com.google.gson.annotations.SerializedName
import ru.eruditeonline.app.data.remote.model.base.ApiScore

class ApiTempResult(
    /** Временный идентификатор прохождения теста */
    @SerializedName("id") val id: Int?,
    /** Выбранные ответы */
    @SerializedName("answers") val answers: List<ApiResultAnswer>?,
    /** Набранные баллы */
    @SerializedName("score") val score: ApiScore?,
    /** Затраченное время, в секундах */
    @SerializedName("spentTime") val spentTime: Long?,
    /** Данные результата (null если олимпиада) */
    @SerializedName("resultInfo") val resultInfo: ApiResultInfo?,
)
