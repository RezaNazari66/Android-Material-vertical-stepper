package com.learnina.materialverticalstepper.verticalstepper

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import com.learnina.materialverticalstepper.R
import com.learnina.materialverticalstepper.databinding.VerticalStepperBinding
class VerticalStepper @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var binding: VerticalStepperBinding
    private var adapter : VerticalStepperAdapter? = null
    init {
        val inflater = LayoutInflater.from(context)

        binding = DataBindingUtil.inflate(inflater, R.layout.vertical_stepper, this, true)
        attrs?.let {

            binding.rvStepper.adapter

        }


    }



    fun setStepperStepList(listOfItem: List<VerticalItem>, fragmentManager: FragmentManager) {
         adapter = VerticalStepperAdapter(fragmentManager, context)

        listOfItem[0].apply {
            isOpen=true
            isActive=true
        }

        adapter?.submitList(listOfItem)

        binding.rvStepper.adapter = adapter
    }

    fun setButtonText(nextBtnText: String, prevBtnText: String , finishBtnText : String){
        finishText = finishBtnText
        prevText = prevBtnText
        nextText = nextBtnText

    }

    fun setButtonColor(activeBtnColor: Int , inActiveBtnColor: Int){
        activeColor = activeBtnColor
        inActiveColor = inActiveBtnColor
    }

    fun getAdapter(): VerticalStepperAdapter?{
        return adapter
    }

}