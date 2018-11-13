package com.redugsi.weatherapp

import android.os.Bundle
import com.redugsi.weatherapp.ui.common.BaseInjectorActivity
import com.redugsi.weatherapp.ui.common.navigation.MainActivityNavigationController
import javax.inject.Inject

class MainActivity : BaseInjectorActivity() {

    @Inject
    lateinit var navigator: MainActivityNavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
