package ru.eruditeonline.app.data.mapper

import android.graphics.Color
import ru.eruditeonline.app.data.model.base.Country
import ru.eruditeonline.app.data.model.base.Diploma
import ru.eruditeonline.app.data.model.base.Score
import ru.eruditeonline.app.data.remote.model.base.ApiCountry
import ru.eruditeonline.app.data.remote.model.base.ApiDiploma
import ru.eruditeonline.app.data.remote.model.base.ApiScore
import ru.eruditeonline.core.domain.ext.orDefault
import javax.inject.Inject

class BaseMapper @Inject constructor() {

    fun parseColor(color: String?): Int? {
        return try {
            Color.parseColor(color.orEmpty())
        } catch (e: Exception) {
            null
        }
    }

    fun fromApiToModel(api: ApiScore?): Score {
        return Score(
            current = api?.current.orDefault(),
            max = api?.max.orDefault(),
            color = api?.color,
        )
    }

    fun fromApiToModel(api: ApiCountry): Country {
        return Country(
            name = api.name.orEmpty(),
            code = api.code.orEmpty(),
            image = api.image.orEmpty(),
        )
    }

    fun fromApiToModel(api: ApiDiploma): Diploma {
        return Diploma(
            type = api.type.orEmpty(),
            image = api.image.orEmpty(),
        )
    }
}
