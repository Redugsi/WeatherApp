package com.redugsi.weatherapp.ui.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.redugsi.weatherapp.ui.camera.CameraFragment
import com.redugsi.weatherapp.ui.home.HomeFragment
import com.redugsi.weatherapp.ui.settings.SettingsFragment

class MainPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {

    companion object {
        val PAGE_COUNT = 2
    }

    override fun getItem(index: Int): Fragment {
        return when(index){
            0-> HomeFragment.newInstance()
            else-> HomeFragment.newInstance()
        }
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

}