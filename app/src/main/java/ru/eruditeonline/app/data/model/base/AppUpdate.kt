package ru.eruditeonline.app.data.model.base

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class AppUpdate(
    val forceUpdate: Boolean,
) : Parcelable
