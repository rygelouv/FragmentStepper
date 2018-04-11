package com.rygelouv.fragmentstepperproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.ProgressBar
import android.widget.Toast
import com.rygelouv.fragmentstepper.FragmentStepper
import com.rygelouv.fragmentstepper.StepsManager

class MainActivity : AppCompatActivity(), OnFragmentInteractionListener, StepsManager {

    lateinit var stepper: FragmentStepper
    lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stepper = findViewById(R.id.stepper)
        progressBar = findViewById(R.id.progress_bar)

        stepper.setParentActivity(this)
        stepper.stepsChangeListener = object : FragmentStepper.StepsChangeListener {
            override fun onStepChanged(stepNumber: Int) {
                Toast.makeText(this@MainActivity, "Page changed", Toast.LENGTH_SHORT).show()
                progressBar.progress = stepper.currentItem
            }
        }
    }

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

    override fun onFragmentInteraction() {
        stepper.goToNexStep()
    }

    override fun onBackPressed() {
        if (stepper.isLastStep()) super.onBackPressed() else stepper.goToPreviousStep()
    }
}
