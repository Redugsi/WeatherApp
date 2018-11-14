package com.redugsi.weatherapp.ui.common.navigation

import com.redugsi.weatherapp.MainActivity
import com.redugsi.weatherapp.R
import com.redugsi.weatherapp.ui.common.BaseFragment
import com.redugsi.weatherapp.ui.main.MainFragment
import com.redugsi.weatherapp.ui.settings.SettingsFragment
import javax.inject.Inject

class MainActivityNavigationController @Inject constructor(var activity: MainActivity): BaseActivityNavigationController(activity){

    init {
        this.containerId = R.id.container_main
    }

    fun toMain(){
        replace(BaseFragment.newInstance(MainFragment()))
    }

    fun toSettings(){
        add(BaseFragment.newInstance(SettingsFragment()))
    }

    fun toCamera(){
        activity.openCamera()
    }
}