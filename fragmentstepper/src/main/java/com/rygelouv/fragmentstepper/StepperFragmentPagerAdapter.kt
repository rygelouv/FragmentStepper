package com.rygelouv.fragmentstepper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by rygelouv on 4/7/18.
 * <p>
 * FragmentStepperProject
 * Copyright (c) 2018 Makeba Inc All rights reserved.
 */
class StepperFragmentPagerAdapter(fm: FragmentManager, private var stepsManager: StepsManager): SmartFragmentStatePagerAdapter(fm)
{
    override fun getItem(position: Int): Fragment {
        return stepsManager.getStep(position)
    }

    override fun getCount(): Int {
        return stepsManager.getCount()
    }
}