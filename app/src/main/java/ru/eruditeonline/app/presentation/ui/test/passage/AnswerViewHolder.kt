package ru.eruditeonline.app.presentation.ui.test.passage

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.eruditeonline.app.R
import ru.eruditeonline.app.data.model.test.Answer
import ru.eruditeonline.app.databinding.ItemTestQuestionAnswerBinding
import ru.eruditeonline.app.presentation.extension.inflate
import ru.eruditeonline.app.presentation.extension.load
import ru.eruditeonline.app.presentation.extension.setTextFromHtml

class AnswerViewHolder(
    parent: ViewGroup,
    private val onItemClick: (Answer) -> Unit,
) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_test_question_answer)) {
    private val binding by viewBinding(ItemTestQuestionAnswerBinding::bind)

    @SuppressLint("ClickableViewAccessibility")
    fun bind(answer: Answer, isSelected: Boolean) = with(binding) {
        textView.setTextFromHtml(answer.text)
        imageView.isVisible = !answer.image.isNullOrEmpty()
        if (imageView.isVisible) {
            imageView.load(answer.image)
        }
        radioButton.isChecked = isSelected
        clickableView.setOnClickListener {
            onItemClick(answer)
        }
    }
}
