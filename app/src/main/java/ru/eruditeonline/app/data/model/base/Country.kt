package ru.eruditeonline.app.data.model.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Country(
    val name: String,
    val code: String,
    val image: String,
) : Parcelable
