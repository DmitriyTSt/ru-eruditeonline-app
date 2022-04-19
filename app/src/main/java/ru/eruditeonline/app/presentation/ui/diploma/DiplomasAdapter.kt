package ru.eruditeonline.app.presentation.ui.diploma

import android.view.ViewGroup
import ru.eruditeonline.app.data.model.base.Diploma
import ru.eruditeonline.app.presentation.ui.base.BaseAdapter
import javax.inject.Inject

class DiplomasAdapter @Inject constructor() : BaseAdapter<Diploma, DiplomaViewHolder>() {

    lateinit var onItemClick: (Diploma) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiplomaViewHolder {
        return DiplomaViewHolder(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: DiplomaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
