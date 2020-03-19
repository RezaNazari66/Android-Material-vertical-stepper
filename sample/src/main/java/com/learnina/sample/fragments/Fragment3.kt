package com.learnina.sample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.learnina.materialverticalstepper.verticalstepper.StepperFragment

import com.learnina.sample.R
import kotlinx.android.synthetic.main.fragment2.*
import kotlinx.android.synthetic.main.fragment3.*


class Fragment3 : StepperFragment() {

    override fun checkFieldsValidation(): Boolean {
        return if (et_age.text.isNotEmpty()){
            true
        }else{
            Toast.makeText(context,"please enter age", Toast.LENGTH_SHORT).show()
            false
        }
    }

    override fun finishButtonAction() {
        //when finish button pressed
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment3, container, false)

    }

}
