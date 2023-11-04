package ru.eruditeonline.app.data.mapper

import ru.eruditeonline.app.data.model.profile.Profile
import ru.eruditeonline.app.data.remote.model.profile.ApiProfile
import javax.inject.Inject

class ProfileMapper @Inject constructor(
    private val baseMapper: BaseMapper,
) {

    fun fromApiToModel(api: ApiProfile): Profile {
        return Profile(
            id = api.id.orDefault(),
            email = api.email.orEmpty(),
            name = api.name.orEmpty(),
            surname = api.surname.orEmpty(),
            patronymic = api.patronymic,
            avatar = api.avatar,
            country = api.country?.let { baseMapper.fromApiToModel(it) },
            region = api.region,
            city = api.city.orEmpty(),
            school = api.school,
        )
    }
}
