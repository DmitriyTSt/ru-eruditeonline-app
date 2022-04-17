package ru.eruditeonline.app.data.mapper

import ru.eruditeonline.app.data.model.profile.Profile
import ru.eruditeonline.app.data.remote.model.profile.ApiProfile
import javax.inject.Inject

class ProfileMapper @Inject constructor() {

    fun fromApiToModel(api: ApiProfile): Profile {
        return Profile(
            id = api.id.orDefault(),
            name = api.name.orEmpty(),
            surname = api.surname.orEmpty(),
            patronymic = api.patronymic,
            avatar = api.avatar,
        )
    }
}
