package ru.eruditeonline.app.presentation.ui.test.passage

import android.view.ViewGroup
import ru.eruditeonline.app.data.model.test.Answer
import ru.eruditeonline.ui.presentation.base.BaseAdapter
import javax.inject.Inject

class AnswersAdapter @Inject constructor() : BaseAdapter<Answer, AnswerViewHolder>() {

    var selectedAnswerId: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerViewHolder {
        return AnswerViewHolder(parent, ::selectAnswer)
    }

    override fun onBindViewHolder(holder: AnswerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, item.id == selectedAnswerId)
    }

    private fun selectAnswer(answer: Answer) {
        if (selectedAnswerId == answer.id) return

        val oldSelectedId = items.indexOfFirst { it.id == selectedAnswerId }.takeIf { it >= 0 }
        val newSelectedId = items.indexOf(answer)
        selectedAnswerId = answer.id
        if (oldSelectedId != null) {
            notifyItemChanged(oldSelectedId)
        }
        notifyItemChanged(newSelectedId)
    }
}
