package ru.eruditeonline.app.data.model

class ParsedError(
    val code: String,
    val title: String,
    val message: String,
) {
    companion object {
        var DEFAULT_TITLE = ""
        var DEFAULT_MESSAGE = ""
    }
}
