package ru.eruditeonline.app.data.model.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Diploma(
    val type: String,
    val image: String,
) : Parcelable
