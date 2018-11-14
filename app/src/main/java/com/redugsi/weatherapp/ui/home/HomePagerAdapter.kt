package com.redugsi.weatherapp.ui.home

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import com.redugsi.weatherapp.ui.weather.WeatherFragment

class HomePagerAdapter(fm: FragmentManager?, pagerAdapterItemAddListener: PagerAdapterItemAddListener?) : FragmentStatePagerAdapter(fm) {

    var fragments: ArrayList<Fragment> = ArrayList()
    private var pagerAdapterItemAddListener: PagerAdapterItemAddListener? = null

    init {
        fragments.add(WeatherFragment.newInstance())
        this.pagerAdapterItemAddListener = pagerAdapterItemAddListener
    }

    fun addNewFragment(fragment: Fragment){
        fragments.add(fragment)
        notifyDataSetChanged()
        pagerAdapterItemAddListener?.onItemAdded()
    }

    override fun getItem(index: Int): Fragment {
        return fragments[index]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}