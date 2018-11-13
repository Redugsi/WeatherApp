package com.redugsi.weatherapp.ui.common.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import kotlin.properties.Delegates

abstract class BaseNavigationController{

    protected var containerId: Int by Delegates.notNull<Int>()
    protected lateinit var fragmentManager: FragmentManager

    protected fun add(fragment: Fragment){
        fragmentManager.beginTransaction()
                .add(containerId, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

    protected fun replace(fragment: Fragment) {
        fragmentManager.beginTransaction()
                .replace(containerId, fragment)
                .commitAllowingStateLoss()
    }

    protected fun replaceAndAddToBackStack(fragment: Fragment, tag: String) {
        fragmentManager.beginTransaction()
                .replace(containerId, fragment, tag)
                .addToBackStack(null)
                .commitAllowingStateLoss()
    }

    protected fun clearHistory() {
        try {
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        } catch (ignored: IllegalStateException) {

        }

    }
}