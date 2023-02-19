package com.example.mafia.extensions

import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.widget.ImageView

fun ImageView.setImageDrawableWithAnimation(drawable: Drawable?, duration: Int = 250) {
    val currentDrawable = getDrawable()
    if (currentDrawable == null) {
        setImageDrawable(drawable)
        return
    }

    val transitionDrawable = TransitionDrawable(arrayOf(
        currentDrawable,
        drawable
    ))
    setImageDrawable(transitionDrawable)
    transitionDrawable.startTransition(duration)
}