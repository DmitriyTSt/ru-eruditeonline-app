package ru.eruditeonline.app.data.model

import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import ru.eruditeonline.app.data.remote.response.ErrorResponse
import timber.log.Timber
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class ParsedError(val code: String, val message: String) {
    companion object {
        var DEFAULT_CODE = "SOMETHING_WRONG"
        var DEFAULT_MESSAGE = ""
    }

    class NetworkError(code: String, message: String) : ParsedError(code, message)
    class GeneralError(code: String, message: String) : ParsedError(code, message)
    class ApiError(code: String, message: String) : ParsedError(code, message)
}

fun Throwable.parseError(): ParsedError {
    Timber.e(this)
    val code = ParsedError.DEFAULT_CODE
    val message = ParsedError.DEFAULT_MESSAGE

    return when {
        isNetworkError -> ParsedError.NetworkError(code, message)
        /** General Http error */
        this is HttpException -> {
            val body = response()?.errorBody()
            val gson = GsonBuilder().create()
            var error: ParsedError? = null
            try {
                val apiError = gson.fromJson(body?.string(), ErrorResponse::class.java)
                response()?.code()?.let { error = apiError?.toParsedError() }
            } catch (ioEx: IOException) {
                Timber.e(ioEx)
            } catch (isEx: IllegalStateException) {
                Timber.e(isEx)
            } catch (isEx: JsonSyntaxException) {
                Timber.e(isEx)
            }
            error ?: ParsedError.GeneralError(code, message)
        }
        else -> {
            ParsedError.GeneralError(code, message)
        }
    }
}

private fun ErrorResponse.toParsedError(): ParsedError {
    return ParsedError.ApiError(
        code = error?.code ?: ParsedError.DEFAULT_CODE,
        message = error?.message ?: ParsedError.DEFAULT_MESSAGE,
    )
}

private val Throwable.isNetworkError: Boolean
    get() = when (this) {
        is ConnectException,
        is UnknownHostException,
        is SocketTimeoutException,
        -> true
        else -> false
    }
