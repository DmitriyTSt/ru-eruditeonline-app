package ru.eruditeonline.app.presentation.extension

import android.widget.ImageView
import ru.eruditeonline.app.R

fun ImageView.setDifficulty(difficulty: Int) {
    setImageResource(
        when (difficulty) {
            1 -> R.drawable.ic_rating_1
            2 -> R.drawable.ic_rating_2
            3 -> R.drawable.ic_rating_3
            4 -> R.drawable.ic_rating_4
            5 -> R.drawable.ic_rating_5
            else -> 0
        }
    )
}
