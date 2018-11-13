package com.redugsi.weatherapp.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    companion object {
        val PAGE_COUNT = 3
    }

    override fun getItem(p0: Int): Fragment {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

}