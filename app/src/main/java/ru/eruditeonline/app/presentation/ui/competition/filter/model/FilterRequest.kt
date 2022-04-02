package ru.eruditeonline.app.presentation.ui.competition.filter.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class FilterRequest(
    val ageIds: List<String>,
    val subjectIds: List<String>,
) : Parcelable
