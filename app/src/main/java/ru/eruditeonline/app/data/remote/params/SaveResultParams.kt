package ru.eruditeonline.app.data.remote.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaveResultParams(
    /** Временный идентификатор прохождения теста */
    @SerialName("completeId") val completeId: Int,
    /** Имя */
    @SerialName("name") val name: String,
    /** Фамилия */
    @SerialName("surname") val surname: String,
    /** Отчество */
    @SerialName("patronymic") val patronymic: String? = null,
    /** Образовательное учреждение */
    @SerialName("school") val school: String? = null,
    /** Должность */
    @SerialName("position") val position: String? = null,
    /** Руководитель */
    @SerialName("teacher") val teacher: String? = null,
    /** Страна (выбор из списка) */
    @SerialName("country") val country: String,
    /** Населенный пункт */
    @SerialName("city") val city: String,
    /** Регион */
    @SerialName("region") val region: String? = null,
    /** E-mail */
    @SerialName("email") val email: String,
    /** E-mail руководителя */
    @SerialName("teacherEmail") val teacherEmail: String? = null,
    /** Идентифифкатор типа диплома */
    @SerialName("diplomaType") val diplomaType: String,
    /** Оценка */
    @SerialName("review") val review: Review,
) {
    @Serializable
    data class Review(
        /** Качество и понятность вопросов */
        @SerialName("quality") val quality: Int? = null,
        /** Сложность вопросов */
        @SerialName("difficulty") val difficulty: Int? = null,
        /** Насколько интересными были вопросы */
        @SerialName("interest") val interest: Int? = null,
    )
}
