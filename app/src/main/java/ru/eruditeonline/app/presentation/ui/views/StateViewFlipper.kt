package ru.eruditeonline.app.presentation.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ViewFlipper
import androidx.annotation.StringRes
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.LoadableState
import ru.eruditeonline.app.data.model.ParsedError
import ru.eruditeonline.app.databinding.ViewErrorStateBinding
import ru.eruditeonline.app.databinding.ViewLoadingStateBinding

class StateViewFlipper(context: Context, attrs: AttributeSet? = null) : ViewFlipper(context, attrs) {

    enum class State(val displayedChild: Int) {
        LOADING(0),
        ERROR(1),
        DATA(2),
        CUSTOM(3),
    }

    private val loadingBinding = ViewLoadingStateBinding.inflate(LayoutInflater.from(context), this, true)
    private val errorBinding = ViewErrorStateBinding.inflate(LayoutInflater.from(context), this, true)

    fun <T> setState(loadableResult: LoadableState<T>) {
        when (loadableResult) {
            is LoadableState.Loading -> setStateLoading()
            is LoadableState.Success -> setStateData()
            is LoadableState.Error -> setStateError(loadableResult.error)
        }
    }

    fun setRetryMethod(retry: () -> Unit) {
        errorBinding.buttonRepeat.setOnClickListener { retry.invoke() }
    }

    private fun setStateLoading() {
        displayedChild = State.LOADING.displayedChild
    }

    private fun setStateData() {
        displayedChild = State.DATA.displayedChild
    }

    private fun setStateError(error: ParsedError) {
        displayedChild = State.ERROR.displayedChild
        when (error) {
            is ParsedError.ApiError -> setApiError(error.message)
            is ParsedError.GeneralError -> setGeneralError()
            is ParsedError.NetworkError -> setNetworkError()
        }
    }

    private fun setNetworkError() {
        setErrorStateContent(
            titleRes = R.string.error_no_network_title,
            description = context.getString(R.string.error_no_network_description),
        )
    }

    private fun setGeneralError() {
        setErrorStateContent(
            titleRes = R.string.error_something_wrong_title,
            description = context.getString(R.string.error_something_wrong_description),
        )
    }

    private fun setApiError(description: String) {
        setErrorStateContent(
            titleRes = R.string.error_something_wrong_title,
            description = description,
        )
    }

    private fun setErrorStateContent(@StringRes titleRes: Int, description: String) = with(errorBinding) {
        textViewErrorTitle.setText(titleRes)
        textViewErrorMessage.text = description
    }
}
