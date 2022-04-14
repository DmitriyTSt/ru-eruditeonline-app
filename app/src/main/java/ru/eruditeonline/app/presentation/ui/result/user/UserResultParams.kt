package ru.eruditeonline.app.presentation.ui.result.user

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class UserResultParams : Parcelable {
    @Parcelize
    class Email(val email: String) : UserResultParams()

    @Parcelize
    class Query(val query: String) : UserResultParams()

    @Parcelize
    object All : UserResultParams()
}
