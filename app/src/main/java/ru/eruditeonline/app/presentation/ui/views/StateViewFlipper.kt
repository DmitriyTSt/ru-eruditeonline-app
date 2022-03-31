package ru.eruditeonline.app.presentation.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ViewFlipper
import ru.eruditeonline.app.data.model.LoadState
import ru.eruditeonline.app.data.model.ParsedError
import ru.eruditeonline.app.databinding.ViewErrorStateBinding
import ru.eruditeonline.app.databinding.ViewLoadingStateBinding

class StateViewFlipper(context: Context, attrs: AttributeSet? = null) : ViewFlipper(context, attrs) {

    enum class State(val displayedChild: Int) {
        LOADING(0),
        ERROR(1),
        DATA(2),
        CUSTOM(3)
    }

    private val loadingBinding = ViewLoadingStateBinding.inflate(LayoutInflater.from(context), this, true)
    private val errorBinding = ViewErrorStateBinding.inflate(LayoutInflater.from(context), this, true)

    fun <T> setStateFromResult(loadableResult: LoadState<T>, useApiErrorMessage: Boolean = false) {
        when (loadableResult) {
            is LoadState.Loading -> setStateLoading()
            is LoadState.Success -> setStateData()
            is LoadState.Error -> setStateError(loadableResult.error)
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
        errorBinding.textViewErrorTitle.text = error.title
        errorBinding.textViewErrorMessage.text = error.message
    }
}
