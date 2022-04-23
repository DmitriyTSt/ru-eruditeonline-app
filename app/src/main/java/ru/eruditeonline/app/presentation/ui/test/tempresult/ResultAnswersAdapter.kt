package ru.eruditeonline.app.presentation.ui.test.tempresult

import android.view.ViewGroup
import ru.eruditeonline.app.data.model.test.ResultAnswer
import ru.eruditeonline.app.presentation.ui.base.BaseAdapter
import javax.inject.Inject

class ResultAnswersAdapter @Inject constructor() : BaseAdapter<ResultAnswer, ResultAnswerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultAnswerViewHolder {
        return ResultAnswerViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ResultAnswerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}