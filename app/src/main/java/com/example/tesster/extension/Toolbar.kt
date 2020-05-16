package com.example.tesster.extension

import android.view.ViewManager
import androidx.appcompat.widget.Toolbar
import org.jetbrains.anko.custom.ankoView

fun ViewManager.toolbar(theme: Int = 0) = toolbar(theme) {}

inline fun ViewManager.toolbar(theme: Int = 0, init: Toolbar.() -> Unit) =
    ankoView({ Toolbar(it) }, theme, init)