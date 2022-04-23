package ru.eruditeonline.app.presentation.ui.test.tempresult

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.test.ResultAnswer
import ru.eruditeonline.app.databinding.ItemResultAnswerBinding
import ru.eruditeonline.app.presentation.extension.inflate

class ResultAnswerViewHolder(
    parent: ViewGroup,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_result_answer)) {
    private val binding by viewBinding(ItemResultAnswerBinding::bind)

    @SuppressLint("SetTextI18n")
    fun bind(answer: ResultAnswer) = with(binding) {
        root.text = "${answer.question.title}. ${answer.answerText ?: root.context.getString(R.string.empty_answer)}"
    }
}
