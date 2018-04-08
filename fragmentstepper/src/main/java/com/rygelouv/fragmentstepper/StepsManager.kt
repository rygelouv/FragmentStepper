package com.rygelouv.fragmentstepper

import android.support.v4.app.Fragment

/**
 * Created by rygelouv on 4/8/18.
 * <p>
 * FragmentStepperProject
 * Copyright (c) 2018 Makeba Inc All rights reserved.
 */
interface StepsManager {
    fun getCount(): Int
    fun getStep(position: Int): Fragment
}