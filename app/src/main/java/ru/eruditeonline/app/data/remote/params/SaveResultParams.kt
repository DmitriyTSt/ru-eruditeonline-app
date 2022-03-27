package ru.eruditeonline.app.data.remote.params

import com.google.gson.annotations.SerializedName

class SaveResultParams(
    /** Временный идентификатор прохождения теста */
    @SerializedName("completeId") val completeId: Long,
    /** Имя */
    @SerializedName("name") val name: String,
    /** Фамилия */
    @SerializedName("surname") val surname: String,
    /** Отчество */
    @SerializedName("patronymic") val patronymic: String?,
    /** Образовательное учреждение */
    @SerializedName("school") val school: String?,
    /** Должность */
    @SerializedName("position") val position: String?,
    /** Руководитель */
    @SerializedName("teacher") val teacher: String?,
    /** Страна (выбор из списка) */
    @SerializedName("country") val country: String,
    /** Населенный пункт */
    @SerializedName("city") val city: String,
    /** Регион */
    @SerializedName("region") val region: String?,
    /** E-mail */
    @SerializedName("email") val email: String,
    /** E-mail руководителя */
    @SerializedName("teacherEmail") val teacherEmail: String?,
    /** Идентифифкатор типа диплома */
    @SerializedName("diplomaType") val diplomaType: String,
    /** Оценка */
    @SerializedName("review") val review: Review,
) {
    class Review(
        /** Качество и понятность вопросов */
        @SerializedName("quality") val quality: Int?,
        /** Сложность вопросов */
        @SerializedName("difficulty") val difficulty: Int?,
        /** Насколько интересными были вопросы */
        @SerializedName("interest") val interest: Int?,
    )
}
