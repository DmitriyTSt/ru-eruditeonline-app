package ru.eruditeonline.app.data.model.profile

import ru.eruditeonline.app.data.model.base.Country

class Profile(
    val id: Int,
    val email: String,
    val name: String,
    val surname: String,
    val patronymic: String?,
    val avatar: String?,
    val country: Country?,
    val region: String?,
    val city: String,
    val school: String?,
)
