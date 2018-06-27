package com.rygelouv.fragmentstepperproject.testcustomviews

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.rygelouv.fragmentstepperproject.R

class TestCustomViewActivity : AppCompatActivity() {

    lateinit var text1: TextMultiStateView
    lateinit var text2: TextMultiStateView
    lateinit var text3: TextMultiStateView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_custom_view)
        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)
        text3 = findViewById(R.id.text3)

        text1.text.text = "Upload profile picture"
        text2.text.text = "Upload document"
        text3.text.text = "Validate process"

        Handler().postDelayed({
            text1.setState(State.LOADING)
            start()
        }, 5000)
    }

    private fun start()
    {
        // We simulate a running task
        Handler().postDelayed({
            text1.setState(State.DONE)
            start2()
        }, 3000)
    }

    private fun start2() {
        text2.setState(State.LOADING)
        Handler().postDelayed({
            text2.setState(State.DONE)
            start3()
        }, 3000)
    }

    private fun start3() {
        text3.setState(State.LOADING)
        Handler().postDelayed({
            text3.setState(State.FAILED)
        }, 3000)
    }
}
