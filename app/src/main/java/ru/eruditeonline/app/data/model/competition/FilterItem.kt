package ru.eruditeonline.app.data.model.competition

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import ru.eruditeonline.app.data.model.Similarable

/**
 * Элемент фильтра
 */
@Parcelize
data class FilterItem(
    /** Идентификатор */
    val id: String,
    /** Название */
    val title: String,
    /** Выбран ли фильтр */
    var selected: Boolean,
) : Similarable<FilterItem>, Parcelable {
    override fun areItemsTheSame(other: FilterItem): Boolean {
        return this.id == other.id
    }

    override fun areContentsTheSame(other: FilterItem): Boolean {
        return this == other
    }
}
