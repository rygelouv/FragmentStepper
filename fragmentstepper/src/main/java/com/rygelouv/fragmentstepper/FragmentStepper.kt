package com.rygelouv.fragmentstepper

import android.content.Context
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Scroller


/**
 * Created by rygelouv on 4/6/18.
 * <p>
 * FragmentStepperProject
 *
 * This method JUST determines whether we want to intercept the motion.
 * If we return true, onMotionEvent will be called and we do the actual
 * scrolling there.
 *
 * Found here : https://stackoverflow.com/questions/9650265/how-do-disable-paging-by-swiping-with-finger-in-viewpager-but-still-be-able-to-s
 *
 *  Copyright 2017 Rygelouv.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
class FragmentStepper: ViewPager {

    lateinit var pagerAdapter: StepperFragmentPagerAdapter
    lateinit var stepsChangeListener: StepsChangeListener
    private lateinit var activity: AppCompatActivity

    constructor(context: Context) : super(context){
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
        init()
    }

    override fun onInterceptHoverEvent(event: MotionEvent?): Boolean {
        return false
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return false
    }

    private fun init()
    {
        setCustomScroller()
        this.addOnPageChangeListener(object : OnPageChangeListener {
            override fun onPageScrollStateChanged(position: Int) {
                stepsChangeListener.onStepChanged(position)
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {}
        })
    }

    fun goToNexStep() {
        currentItem += 1
    }

    fun goToPreviousStep() {
        currentItem -= 1
    }

    fun isLastStep(): Boolean = currentItem == 0

    fun setParentActivity(activity: AppCompatActivity)
    {
        if (activity is StepsManager) {
            this.activity = activity
            pagerAdapter = StepperFragmentPagerAdapter(activity.supportFragmentManager, activity)
            adapter = pagerAdapter
        }
        else throw ActivityNotStepsManagerException()
    }

    private fun setCustomScroller() {
        try {
            val viewPager= ViewPager::class.java
            val scroller = viewPager.getDeclaredField("mScroller")
            scroller.isAccessible = true
            scroller.set(this, CustomScroller(context))
        }catch (e: Exception) {
            e.printStackTrace()
        }
    }

    class CustomScroller(context: Context?) : Scroller(context) {
        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, 350)
        }
    }

    interface StepsChangeListener{
        fun onStepChanged(stepNumber: Int)
    }
}
