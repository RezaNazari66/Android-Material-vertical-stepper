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
 
 <p>1. add view to your layout:</p>


    <com.learnina.materialverticalstepper.verticalstepper.VerticalStepper
        android:id="@+id/vertical_list"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
        android:orientation="vertical"
         />
<p>2. create your fragment as step of stepper but instead of extending Fragment() extend StepperFragment()	

	class Fragment1 : StepperFragment()

<p>3. override checkFieldsValidation() method and check inputs ,if all inputs are completed and valid return true
	
	
    override fun checkFieldsValidation() :  Boolean{
        return if (et_name.text.isNotEmpty()){
            true
        }else{
            Toast.makeText(context,"please enter name", Toast.LENGTH_SHORT).show()
            false
        }
    }
    
<p>4. in last fragment ( last stepper item) override finishButtonAction() method. this method will call when you press stepper finish button 	
	
	override fun finishButtonAction() {
         //when finish button pressed
    	}

<p>5. add titles and fragments to a list as stepper items set to setStepperStepList on your activity ( or where you use VerticalStepper view)</p>


        val stepList = mutableListOf<VerticalItem>()

        stepList.add(0, VerticalItem("Name ", "1", Fragment1()) )
        stepList.add(1, VerticalItem("Address", "2", Fragment2()) )
        stepList.add(2, VerticalItem("Age", "3", Fragment3()) )

        stepper.setStepperStepList(stepList, supportFragmentManager)
        stepper.setButtonText("next", "prev", "finish")
