package ru.eruditeonline.architecture.domain

sealed class ParsedError(val code: String, val message: String) {
    companion object {
        var DEFAULT_CODE = "SOMETHING_WRONG"
        var DEFAULT_MESSAGE = ""
    }

    class NetworkError(code: String, message: String) : ParsedError(code, message)
    class GeneralError(code: String, message: String) : ParsedError(code, message)
    class ApiError(code: String, message: String) : ParsedError(code, message)
}
