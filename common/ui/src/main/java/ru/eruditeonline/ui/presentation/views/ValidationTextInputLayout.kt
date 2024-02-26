package ru.eruditeonline.ui.presentation.views

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputLayout
import ru.eruditeonline.ui.R
import ru.eruditeonline.ui.presentation.ext.errorCleaner

/**
 * Класс с возможностью валидировать введенные данные
 * @see validate
 *
 * Поле может быть пустым, если атрибут errorEmpty не выставлен
 * Поле проверяется на правильность, если атрибут validationPattern (регулярное выражение) выставлен
 */
class ValidationTextInputLayout : TextInputLayout, TextInputValidator {
    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    private var validationPattern: String = ""
    private var isNotMalformed: (String) -> Boolean = {
        if (validationPattern.isEmpty()) {
            true
        } else {
            text.contains(validationPattern.toRegex())
        }
    }
    private var malformedError: String = ""
    private var isNotEmpty: (String) -> Boolean = {
        if (emptyError.isEmpty()) {
            true
        } else {
            text.isNotEmpty()
        }
    }
    private var emptyError: String = ""

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.theme.obtainStyledAttributes(
                attrs,
                R.styleable.ValidationTextInputLayout,
                0,
                0
            )
            try {
                emptyError = a.getString(R.styleable.ValidationTextInputLayout_errorEmpty) ?: ""
                malformedError = a.getString(R.styleable.ValidationTextInputLayout_errorMalformed) ?: ""
                validationPattern = a.getString(R.styleable.ValidationTextInputLayout_validationPattern) ?: ""
            } finally {
                a.recycle()
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        editText?.errorCleaner(this)
    }

    override fun validate(): Boolean {
        if (!isNotEmpty(text)) {
            error = emptyError
            return false
        }
        if (!isNotMalformed(text)) {
            error = malformedError
            return false
        }
        return true
    }

    override fun getLayoutId(): Int {
        return id
    }

    override val text: String
        get() = editText?.text?.toString().orEmpty()

    fun setEmptyError(emptyError: String) {
        this.emptyError = emptyError
    }
}
