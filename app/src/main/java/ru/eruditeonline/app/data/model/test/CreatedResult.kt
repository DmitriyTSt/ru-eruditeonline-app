package ru.eruditeonline.app.data.model.test

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class CreatedResult(
    /** Идентификатор результата */
    val id: Int,
    /** Участник */
    val username: String,
    /** Постоянная ссылка на результат прохождения */
    val resultLink: String,
    val achievementText: String?,
) : Parcelable
