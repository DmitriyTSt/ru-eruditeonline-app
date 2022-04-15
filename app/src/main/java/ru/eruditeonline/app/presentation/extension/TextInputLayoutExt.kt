package ru.eruditeonline.app.presentation.extension

import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout
import ru.eruditeonline.app.presentation.ui.views.TextInputValidator

fun TextInputLayout.showError(@StringRes stringRes: Int) = showError(context.getString(stringRes))

fun TextInputLayout.showError(errorText: String) {
    if (error != errorText) {
        error = errorText
    }
}

fun TextInputLayout.hideError() {
    error = null
}

fun TextInputLayout.isError(): Boolean {
    return error != null
}

fun validateAllFields(vararg validator: TextInputValidator): Boolean {
    var result = true
    validator.forEach { layout ->
        result = result and layout.validate()
    }
    return result
}
