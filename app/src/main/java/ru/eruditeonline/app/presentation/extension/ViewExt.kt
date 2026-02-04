package ru.eruditeonline.app.presentation.extension

import android.graphics.Rect
import android.view.View
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

fun View.doOnApplyWindowInsets(block: (View, WindowInsetsCompat, Rect) -> WindowInsetsCompat) {
    val initialPadding = recordInitialPaddingForView(this)

    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        block(v, insets, initialPadding)
    }

    requestApplyInsetsWhenAttached()
}

/**
 * Применение верхнего и нижнего инсета systemBars и нижнего инсета ime
 *
 * Один метод, так как необходимо обрабатывать нижний systemBars только если нет нижнего ime
 * Их нужно ставить в разные вьюхи, если текстовое поле если в скролящейся области для корректного скрола при фокусе на поле
 * @param contentView вью контента, к которой необходимо добавить нижний инсет для e2e. По умолчанию - сама вью.
 * @param applyKeyboardToRoot применять ли инсет клавитауры к самой вью (иначе к вью контента)
 *  (нужен для того чтобы скролить к полю при открытии клавы)
 * @param onBottomInsetChanged передается значение, аналогичное тому, что добавляется как нижний инсет в contentView.
 *  Если нужно применять клавиатурный инсет к самой вью, то только нижний когда клава не показана,
 *  иначе общий от клавы и систем бара
 */
fun View.fitTopBottomSystemBarsBottomKeyboardInsets(
    contentView: View = this,
    applyKeyboardToRoot: Boolean = false,
    onBottomInsetChanged: (bottomInset: Int) -> Unit = {},
) {
    val contentPaddings = recordInitialPaddingForView(contentView)
    doOnApplyWindowInsets { view, insets, paddings ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
        val systemBarsAndIme = insets.getInsets(WindowInsetsCompat.Type.systemBars() + WindowInsetsCompat.Type.ime())

        view.updatePadding(
            top = systemBars.top + paddings.top,
        )
        if (applyKeyboardToRoot) {
            // отдельно выставляем клави
            view.updatePadding(
                bottom = ime.bottom + paddings.bottom,
            )
            val contentBottomInset = if (!isKeyboardVisible(ime)) {
                systemBars.bottom
            } else {
                0
            }
            if (contentBottomInset > 0) {
                contentView.updatePadding(bottom = contentBottomInset + contentPaddings.bottom)
            }
            onBottomInsetChanged(contentBottomInset)
        } else {
            contentView.updatePadding(bottom = contentPaddings.bottom + systemBarsAndIme.bottom)
            onBottomInsetChanged(systemBarsAndIme.bottom)
        }

        WindowInsetsCompat.Builder(insets).setInsets(
            WindowInsetsCompat.Type.systemBars(),
            Insets.of(
                systemBars.left,
                0,
                systemBars.right,
                if (isKeyboardVisible(ime)) 0 else systemBars.bottom, // пропускаем инсет дальше для снекбара
            )
        ).setInsets(
            WindowInsetsCompat.Type.ime(),
            Insets.of(
                ime.left,
                ime.top,
                ime.right,
                0,
            )
        ).build()
    }
}

/**
 * Метод выставляет у вью паддинг, равный высоте статус бара (верхнему системному инсету).
 * При этом помечает, что обработал top inset
 * */
fun View.fitTopInsetsWithPadding(action: ((insetTop: Int) -> Unit)? = null) {
    this.doOnApplyWindowInsets { view, insets, paddings ->
        val windowInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        val defaultAction = { insetTop: Int -> view.updatePadding(top = insetTop + paddings.top) }
        (action ?: defaultAction)(windowInsets.top)
        WindowInsetsCompat.Builder(insets).setInsets(
            WindowInsetsCompat.Type.systemBars(),
            Insets.of(
                windowInsets.left,
                0,
                windowInsets.right,
                windowInsets.bottom
            )
        ).build()
    }
}

/**
 * Ставит заданной view bottom padding, который равен собственному паддингу вью плюс боттом инсет.
 * Оперирует Type.systemBars() и Type.ime()
 */
fun View.fitBottomInsetsPadding(consumeBottomInset: Boolean = true, onBottomInsetChanged: (bottomInset: Int) -> Unit = {}) {
    doOnApplyWindowInsets { _, windowInsetsCompat, paddings ->
        val windowInsets = windowInsetsCompat.getInsets(
            WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime()
        )

        this.updatePadding(bottom = windowInsets.bottom + paddings.bottom)
        onBottomInsetChanged(windowInsets.bottom)

        WindowInsetsCompat.Builder(windowInsetsCompat).setInsets(
            WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.ime(),
            Insets.of(
                windowInsets.left,
                windowInsets.top,
                windowInsets.right,
                if (consumeBottomInset) 0 else windowInsets.bottom,
            ),
        ).build()
    }
}

/**
 * Можно применять только если вью прикреплена
 */
fun View.isKeyboardVisible(insets: Insets): Boolean {
    val keyboardRatio = 0.25
    val systemScreenHeight = this.rootView.height
    val heightDiff = insets.bottom + insets.top
    return heightDiff > keyboardRatio * systemScreenHeight
}

private fun recordInitialPaddingForView(view: View) =
    Rect(view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom)

private fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}
