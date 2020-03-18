package com.learnina.materialverticalstepper.verticalstepper

import androidx.fragment.app.Fragment

data class VerticalItem(
    var title: String,
    val id: String,
    var fragment: Fragment,
    var isOpen: Boolean = false
)