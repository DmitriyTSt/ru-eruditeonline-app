package ru.eruditeonline.app.presentation.managers

import android.content.Context
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.ParsedError
import javax.inject.Inject

class ErrorTextInitializer @Inject constructor(
    private val context: Context,
) {
    fun setDefaultErrorMessage() {
        ParsedError.DEFAULT_MESSAGE = context.getString(R.string.error_something_wrong_description)
    }
}