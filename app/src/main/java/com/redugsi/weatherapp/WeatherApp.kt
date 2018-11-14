package com.redugsi.weatherapp

import android.app.Activity
import android.app.Application
import android.content.Context
import com.redugsi.weatherapp.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class WeatherApp: Application(), HasActivityInjector{

    companion object {
        lateinit var sInstance: WeatherApp
        lateinit var sContext : Context
        var sCount : Int = 0
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        sContext = this
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector

}