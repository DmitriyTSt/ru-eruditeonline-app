package ru.eruditeonline.app.presentation.extension

import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout

/**
 * Ошибка ввода скрывается при вводе текста
 */
fun EditText.errorCleaner(textInputLayout: TextInputLayout) {
    addTextChangedListener {
        if (textInputLayout.isError()) {
            textInputLayout.hideError()
        }
    }
}
