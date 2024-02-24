package ru.eruditeonline.app.presentation.extension

import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.Px
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.mapper.parseError
import ru.eruditeonline.architecture.domain.ParsedError
import kotlin.math.max

fun Fragment.errorSnackbar(error: ParsedError) {
    showCustomSnackbar(
        if (this is BottomSheetDialogFragment) dialog?.window?.decorView else view,
        when (error) {
            is ParsedError.ApiError -> error.message
            is ParsedError.GeneralError -> requireContext().getString(R.string.error_something_wrong_title)
            is ParsedError.NetworkError -> requireContext().getString(R.string.error_no_network_title)
        }
    )
}

fun Fragment.errorSnackbar(throwable: Throwable) {
    showCustomSnackbar(
        if (this is BottomSheetDialogFragment) dialog?.window?.decorView else view,
        when (val error = throwable.parseError()) {
            is ParsedError.ApiError -> error.message
            is ParsedError.GeneralError -> requireContext().getString(R.string.error_something_wrong_title)
            is ParsedError.NetworkError -> requireContext().getString(R.string.error_no_network_title)
        }
    )
}

fun Fragment.errorSnackbar(message: String, marginBottom: Int = 0) {
    showCustomSnackbar(
        view = if (this is BottomSheetDialogFragment) dialog?.window?.decorView else view,
        message = message,
        marginBottom = marginBottom,
    )
}

private fun showCustomSnackbar(
    view: View?,
    message: String,
    actionText: String? = null,
    @Px marginBottom: Int = 0,
    @ColorRes backgroundTintRes: Int = R.color.red,
    @ColorRes textColor: Int = R.color.white,
    @ColorRes actionTextColor: Int = R.color.white,
    textMaxLines: Int = 3,
    action: () -> Unit = {},
) {
    view?.apply {
        val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE).apply {
            setBackgroundTint(context.getColorCompat(backgroundTintRes))
            setTextColor(context.getColorCompat(textColor))
            setActionTextColor(context.getColorCompat(actionTextColor))

            val wordsCount = message.split("\\s+|\\r|\\n".toRegex()).size
            val calculatedDuration = wordsCount * 300 + 1000
            duration = max(calculatedDuration, 2000)

            if (actionText != null) {
                setAction(actionText) { action() }
            }
        }

        // форматирование текста
        val snackbarView = snackbar.view

        if (marginBottom != 0) {
            val res = snackbarView.context.resources
            val defaultMargin = res.getDimension(R.dimen.margin_16)
            snackbarView.translationY = -(defaultMargin + marginBottom)
        }

        val textViewMessage = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        val typefaceMedium = ResourcesCompat.getFont(context, R.font.nunito_medium_font)
        textViewMessage.typeface = typefaceMedium
        with(textViewMessage) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.text_size_body_2))
            maxLines = textMaxLines
            updatePadding(
                left = resources.getDimensionPixelSize(R.dimen.padding_8),
                right = resources.getDimensionPixelSize(R.dimen.padding_8)
            )
            text = message
        }
        val textViewAction = snackbarView.findViewById(com.google.android.material.R.id.snackbar_action) as TextView
        textViewAction.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.text_size_body_2))

        snackbar.show()
    }
}
