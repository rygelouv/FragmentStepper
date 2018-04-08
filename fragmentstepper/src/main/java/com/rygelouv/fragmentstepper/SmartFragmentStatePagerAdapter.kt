package com.rygelouv.fragmentstepper

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.util.SparseArray
import android.view.ViewGroup

/**
 * Created by rygelouv on 4/7/18.
 * <p>
 * FragmentStepperProject
 *
 * Android SmartFragmentStatePagerAdapter that intelligently caches the Fragments in the ViewPager
 *
 *
 * Extension of FragmentStatePagerAdapter which intelligently caches
 * all active fragments and manages the fragment lifecycles.
 * Usage involves extending from SmartFragmentStatePagerAdapter as you would any other PagerAdapter.
 *
 * Found here https://gist.github.com/nesquena/c715c9b22fb873b1d259
 */

abstract class SmartFragmentStatePagerAdapter(fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {

    var registeredFragments = SparseArray<Fragment>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    override fun destroyItem(container: ViewGroup, position: Int, item: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, item)
    }

    fun getRegisteredFragment(postion: Int): Fragment
    {
        return registeredFragments.get(postion)
    }
}