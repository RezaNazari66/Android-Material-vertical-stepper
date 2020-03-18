package com.learnina.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learnina.materialverticalstepper.verticalstepper.VerticalItem
import com.learnina.sample.fragments.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val listItem = mutableListOf<VerticalItem>()

        listItem.add(0,
            VerticalItem("title1 ", "1", Fragment1())
        )
        listItem.add(1,
            VerticalItem("title2 ", "2", Fragment2())
        )
        listItem.add(2,
            VerticalItem("title3 ", "3", Fragment3())
        )

        vertical_list.setCustomItemList(listItem, supportFragmentManager)

    }
}
