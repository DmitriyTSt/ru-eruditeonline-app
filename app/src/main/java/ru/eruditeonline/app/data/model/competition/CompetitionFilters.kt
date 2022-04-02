package ru.eruditeonline.app.data.model.competition

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class CompetitionFilters(
    /** Список фильтрации по возрасту */
    val ages: List<FilterItem>,
    /** Список фильтрации по предметам */
    val subjects: List<FilterItem>,
) : Parcelable
