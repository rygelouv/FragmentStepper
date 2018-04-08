package com.rygelouv.fragmentstepper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by rygelouv on 4/7/18.
 * <p>
 * FragmentStepperProject
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