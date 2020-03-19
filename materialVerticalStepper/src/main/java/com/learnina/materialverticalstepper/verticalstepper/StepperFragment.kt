package com.learnina.materialverticalstepper.verticalstepper

import android.widget.Toast
import androidx.fragment.app.Fragment

abstract class StepperFragment() :  Fragment()  {
    abstract fun checkFieldsValidation(): Boolean
    open fun finishButtonAction(){
        Toast.makeText(context, "finish item clicked!", Toast.LENGTH_SHORT).show()
    }
}