package ru.eruditeonline.app.presentation.ui.result.detail

import android.view.ViewGroup
import ru.eruditeonline.app.data.model.test.ResultAnswer
import ru.eruditeonline.app.presentation.ui.base.BaseAdapter
import javax.inject.Inject

class ResultAnswersAdapter @Inject constructor() : BaseAdapter<ResultAnswer, ResultAnswerViewHolder>() {

    /** Развернут ли вопрос, по заголовку вопроса */
    private val expanded = mutableSetOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAnswerViewHolder {
        return ResultAnswerViewHolder(parent) { answer, position ->
            val key = answer.question.title
            if (expanded.contains(key)) {
                expanded.remove(key)
            } else {
                expanded.add(key)
            }
            notifyItemChanged(position)
        }
    }

    override fun onBindViewHolder(holder: ResultAnswerViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, expanded.contains(item.question.title))
    }
}
