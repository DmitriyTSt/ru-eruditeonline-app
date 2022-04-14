package ru.eruditeonline.app.presentation.extension

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.eruditeonline.app.R

/**
 * Модифицированная версия функции для загрузки картинки с помощью Glide
 * Позволяет задавать трансформации, ресурсы для плейсхолдера или ошибки и др. параметры
 */
fun ImageView.load(
    imageUrl: String?,
    @DrawableRes placeHolderRes: Int? = null,
    @DrawableRes errorRes: Int? = placeHolderRes,
    @DrawableRes fallbackRes: Int? = placeHolderRes,
    doOnFailure: () -> Unit = {},
    doOnSuccess: (Drawable?) -> Unit = {},
) {
    Glide.with(this).clear(this)
    Glide.with(context)
        .load(imageUrl)
        .apply { placeHolderRes?.let(::placeholder) }
        .apply { errorRes?.let(::error) }
        .apply { fallbackRes?.let(::fallback) }

        .addListener(
            object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    e?.printStackTrace()
                    doOnFailure.invoke()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    doOnSuccess.invoke(resource)
                    return false
                }
            }
        )
        .into(this)
}

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
