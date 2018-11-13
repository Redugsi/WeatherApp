package com.redugsi.weatherapp.ui.common.navigation

import com.redugsi.weatherapp.MainActivity
import com.redugsi.weatherapp.R
import com.redugsi.weatherapp.ui.home.HomeFragment
import com.redugsi.weatherapp.ui.main.MainFragment
import javax.inject.Inject

class MainActivityNavigationController @Inject constructor(activity: MainActivity): BaseActivityNavigationController(activity){

    init {
        this.containerId = R.id.container_main
    }

    fun toHome(){
        replace(HomeFragment.newInstance())
    }

    fun toMain(){
        replace(MainFragment.newInstance())
    }
}