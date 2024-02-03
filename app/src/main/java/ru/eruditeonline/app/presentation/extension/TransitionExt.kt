package ru.eruditeonline.app.presentation.extension

import androidx.transition.Transition
import androidx.transition.Transition.TransitionListener

fun Transition.doOnEnd(listener: () -> Unit): Transition {
    return addListener(object : TransitionListener {

        override fun onTransitionEnd(transition: Transition) {
            listener()
        }

        override fun onTransitionStart(transition: Transition) = Unit
        override fun onTransitionCancel(transition: Transition) = Unit
        override fun onTransitionPause(transition: Transition) = Unit
        override fun onTransitionResume(transition: Transition) = Unit
    })
}
