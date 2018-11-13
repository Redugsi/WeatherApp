package com.redugsi.weatherapp.ui.common

import android.support.v4.app.Fragment

open class BaseFragmentNavigationController(fragment: Fragment): BaseNavigationController(){

    init {
        this.fragmentManager = fragment.childFragmentManager
    }
}