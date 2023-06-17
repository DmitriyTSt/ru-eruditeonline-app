package ru.eruditeonline.app.presentation.ui.test.tempresult

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.test.ResultAnswer
import ru.eruditeonline.app.databinding.ItemTempResultAnswerBinding
import ru.eruditeonline.app.presentation.extension.inflate

class ResultAnswerViewHolder(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_temp_result_answer)) {
    private val binding by viewBinding(ItemTempResultAnswerBinding::bind)

    @SuppressLint("SetTextI18n")
    fun bind(answer: ResultAnswer) = with(binding) {
        root.text = buildString {
            append("${answer.question.title}. ${answer.answerText ?: root.context.getString(R.string.empty_answer)}")
            if (answer.correct != null && !answer.correct.isCorrect) {
                append("\n")
                append(root.context.getString(R.string.result_corrent_answer_template, answer.correct.answerText))
            }
        }
    }
}
