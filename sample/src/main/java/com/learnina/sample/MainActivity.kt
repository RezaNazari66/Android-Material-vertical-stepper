package com.learnina.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learnina.materialverticalstepper.verticalstepper.VerticalItem
import com.learnina.sample.fragments.Fragment1
import com.learnina.sample.fragments.Fragment2
import com.learnina.sample.fragments.Fragment3
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val stepList = mutableListOf<VerticalItem>()

        stepList.add(0, VerticalItem("Name ", "1", Fragment1()) )
        stepList.add(1, VerticalItem("Address", "2", Fragment2()) )
        stepList.add(2, VerticalItem("Age", "3", Fragment3()) )

        stepper.setStepperStepList(stepList, supportFragmentManager)
        stepper.setButtonText("next", "prev", "finish")
        stepper.setButtonColor(R.color.colorPrimary, R.color.colorAccent)
    }
}
