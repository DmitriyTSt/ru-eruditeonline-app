package ru.eruditeonline.app.presentation.composeui.competition

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ComposeCompetitionViewModel @Inject constructor() : ViewModel() {

    fun init(id: Int) {
        println("INIT ComposeCompetitionViewModel $id $this")
    }
}