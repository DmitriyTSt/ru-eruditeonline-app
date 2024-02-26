package ru.eruditeonline.app.presentation.ui.result.detail

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.test.ResultAnswer
import ru.eruditeonline.app.databinding.ItemResultAnswerBinding
import ru.eruditeonline.ui.presentation.ext.inflate

class ResultAnswerViewHolder(
    parent: ViewGroup,
    private val onItemClick: (ResultAnswer, Int) -> Unit,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_result_answer)) {
    private val binding by viewBinding(ItemResultAnswerBinding::bind)

    @SuppressLint("SetTextI18n")
    fun bind(answer: ResultAnswer, isExpanded: Boolean) = with(binding) {
        textViewAnswer.text = "${answer.question.title}. ${answer.answerText ?: root.context.getString(R.string.empty_answer)}"
        textViewQuestion.text = answer.question.text
        textViewAnswer.setCompoundDrawablesRelativeWithIntrinsicBounds(
            if (isExpanded) {
                R.drawable.ic_arrow_up
            } else {
                R.drawable.ic_arrow_down
            },
            0,
            0,
            0
        )
        textViewQuestion.isVisible = isExpanded
        textViewAnswer.setOnClickListener {
            onItemClick(answer, bindingAdapterPosition)
        }
    }
}
