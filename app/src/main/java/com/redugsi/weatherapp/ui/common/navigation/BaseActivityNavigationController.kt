package com.redugsi.weatherapp.ui.common.navigation

import android.support.v7.app.AppCompatActivity



open class BaseActivityNavigationController(activity: AppCompatActivity): BaseNavigationController() {

    init {
        this.fragmentManager = activity.supportFragmentManager
    }

}