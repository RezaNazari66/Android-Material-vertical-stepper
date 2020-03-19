package com.learnina.materialverticalstepper.verticalstepper

import androidx.fragment.app.Fragment

data class VerticalItem(
    var title: String,
    val id: String,
    var fragment: StepperFragment,
    var isOpen: Boolean = false,
    var isCompleted: Boolean = false,
    var isActive: Boolean = false
)