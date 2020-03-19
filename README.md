# Android-Material-vertical-stepper
Vertical stepper view for android

<p>
<img width="200" alt="screenshot" src="https://github.com/RezaNazari66/Android-Material-vertical-stepper/blob/master/screenshot01.jpg">  <img width="200" alt="screenshot2" src="https://github.com/RezaNazari66/Android-Material-vertical-stepper/blob/master/screenshot3.jpg">
</p>

<h2> Gradle </h2>
<p>Add it in your root build.gradle at the end of repositories:</p>

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.RezaNazari66:Android-Material-vertical-stepper:1.0.0'
	}
  
<h2> Usage </h2>
 
 <p>1. add view to layout:</p>


    <com.learnina.materialverticalstepper.verticalstepper.VerticalStepper
        android:id="@+id/vertical_list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
        android:orientation="vertical"
         />
<p>2.create your fragment as stepper steps but instead of extending Fragment() extend from  StepperFragment()	

	class Fragment1 : StepperFragment()

<p>3. override checkFieldsValidation() method and check inputs on it and if all inputs are completed return true
	
	
    override fun checkFieldsValidation() :  Boolean{
        return if (et_name.text.isNotEmpty()){
            true
        }else{
            Toast.makeText(context,"please enter name", Toast.LENGTH_SHORT).show()
            false
        }
    }
<p>4. on last fragment ( last stepper item) override finishButtonAction() method this method will call when you press stepper finish button 	
	
	override fun finishButtonAction() {
        //when finish button pressed
    	}

<p>5. add titles and fragments as stepper items on your activity ( of where you use VerticalStepper view)</p>


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
