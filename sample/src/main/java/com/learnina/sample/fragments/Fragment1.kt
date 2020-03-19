package com.learnina.sample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.learnina.materialverticalstepper.verticalstepper.StepperFragment

import com.learnina.sample.R
import kotlinx.android.synthetic.main.fragment1.*

class Fragment1 : StepperFragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment1, container, false)
    }


    override fun checkFieldsValidation() :  Boolean{
        return if (et_name.text.isNotEmpty()){
            true
        }else{
            Toast.makeText(context,"please enter name", Toast.LENGTH_SHORT).show()
            false
        }
    }


}
