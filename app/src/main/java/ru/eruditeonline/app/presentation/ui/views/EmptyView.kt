package ru.eruditeonline.app.presentation.ui.views

import android.content.Context
import android.text.Spannable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import ru.eruditeonline.app.R
import ru.eruditeonline.app.databinding.ViewStateEmptyBinding

/**
 * Базовая пустая вью, у которой нужно задать тексты
 */
class EmptyView : ConstraintLayout {

    private val binding: ViewStateEmptyBinding = ViewStateEmptyBinding.inflate(LayoutInflater.from(context), this)

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        binding.buttonEmpty.isVisible = false
        val padding = context.resources.getDimensionPixelSize(R.dimen.padding_16)
        setPadding(padding, padding, padding, padding)

        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.EmptyView,
                0,
                0
            )
            try {
                setEmptyTitle(
                    a.getResourceId(
                        R.styleable.EmptyView_emptyTitle,
                        R.string.empty_default_title
                    )
                )
                setEmptyButton(
                    a.getResourceId(
                        R.styleable.EmptyView_emptyButton,
                        0
                    )
                )
                setEmptyComment(
                    a.getResourceId(
                        R.styleable.EmptyView_emptyComment,
                        R.string.empty_default_message
                    )
                )
            } finally {
                a.recycle()
            }
        }
    }

    fun hideComment() {
        binding.textEmptyComment.isVisible = false
    }

    fun setEmptyComment(@StringRes comment: Int) {
        binding.textEmptyComment.setText(comment)
    }

    fun setEmptyComment(comment: String) {
        binding.textEmptyComment.text = comment
    }

    fun setEmptyComment(comment: Spannable) {
        binding.textEmptyComment.text = comment
    }

    fun setEmptyTitle(@StringRes title: Int) {
        binding.textEmptyTitle.setText(title)
    }

    fun setEmptyTitle(title: String) {
        binding.textEmptyTitle.text = title
    }

    fun setEmptyTitle(title: Spannable) {
        binding.textEmptyTitle.text = title
    }

    fun setButtonTextAndClickListener(@StringRes text: Int, onClickListener: () -> Unit) = with(binding) {
        buttonEmpty.isVisible = true
        buttonEmpty.setText(text)
        buttonEmpty.setOnClickListener { onClickListener() }
    }

    fun setEmptyButtonListener(onClickListener: () -> Unit) = with(binding) {
        buttonEmpty.setOnClickListener { onClickListener() }
    }

    private fun setEmptyButton(@StringRes text: Int) = with(binding) {
        if (text == 0) {
            buttonEmpty.isVisible = false
        } else {
            buttonEmpty.isVisible = true
            buttonEmpty.setText(text)
        }
    }
}
