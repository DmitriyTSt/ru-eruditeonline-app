package ru.eruditeonline.app.data.model.competition

import ru.eruditeonline.app.data.model.Similarable

/**
 * Сокращенная модель конкурса для списков
 */
class CompetitionItemShort(
    /** Идентификатор теста */
    val id: Int,
    /** Название теста */
    val title: String,
    /** Предмет(ы) */
    val subject: String,
    /** Возрастные группы */
    val ages: String,
    /** Сложность (1-5) */
    val difficulty: Int,
    /** Ссылка на изображение */
    val icon: String?,
) : Similarable<CompetitionItemShort> {

    val transitionName: String = "transition_comp_image_$icon"

    override fun areItemsTheSame(other: CompetitionItemShort): Boolean {
        return this.id == other.id
    }

    override fun areContentsTheSame(other: CompetitionItemShort): Boolean {
        return this == other
    }
}
