package com.redugsi.weatherapp.ui.common

import android.support.v7.app.AppCompatActivity
import com.redugsi.weatherapp.R

class MainActivityNavigationController(activity: AppCompatActivity): BaseActivityNavigationController(activity){

    init {
        this.containerId = R.id.container_main
    }
}