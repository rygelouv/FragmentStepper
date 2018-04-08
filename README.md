# Introduction

FragmentStepper is a library that aims to make the process of creating steps screen (wizards) based 
on fragments more easy. The library handles internally:
- Fragments state by using FragmentStatePagerAdapter so your fragment are restored even 
when you navigate back an forth with a good memory management 
- Animations for the transition between steps
- Back an forth Navigation so you won't having to face the deadly deal of backstak management


## Add it to your project

In your project root build.gradle with:
```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and in the app or module build.gradle:

```groovy
dependencies {
    compile 'com.github.rygelouv:fragmentstepper:v0.0.1-beta'
}
```

## How to use

#### Step 1: add it to your layout
```xml
<com.rygelouv.fragmentstepper.FragmentStepper
        android:layout_weight="1"
        android:id="@+id/stepper"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        />
```
#### Step 2: let your activity inherit from `StepsManager` and implements methods
```kotlin
class MainActivity : AppCompatActivity(), StepsManager{
    ...
    
    override fun getCount(): Int {
            return 3
        }
    
        override fun getStep(position: Int): Fragment {
            return when(position){
                0 -> FirstFragment()
                1 -> SecondFragment()
                2 -> ThirdFragment.newInstance("Boom", "Baam")
                else -> FirstFragment()
            }
        }
}
```
#### Step 3: configure stepper
```kotlin
stepper = findViewById(R.id.stepper)
stepper.setParentActivity(this)
        stepper.stepsChangeListener = object : FragmentStepper.StepsChangeListener {
            override fun onStepsChanged() {
                Toast.makeText(this@MainActivity, "Page changed", Toast.LENGTH_SHORT).show()
                // Do whatever you want here
            }
        } 
```
#### Step 4: configure backstack in order to handle navigation
In your activity, override the `onBackPressed()` method and add this code
```kotlin
override fun onBackPressed() {
        if (stepper.currentItem == 0)
            super.onBackPressed()
        else
            stepper.currentItem = stepper.currentItem - 1
    }
```
#### Step 5: there is no step 5. That's it you're all set

## TODO
- Handle animations for transition 
- Improve the manner of handling backstack 

# Credits

Author: Rygel Louv [http://www.rygelouv.wordpress.com/](http://www.rygelouv.wordpress.com/)


License
--------

    Copyright 2017 Rygelouv.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.