package com.redugsi.weatherapp.di

import com.redugsi.weatherapp.ui.camera.TakenPhotoPreviewFragment
import com.redugsi.weatherapp.ui.home.HomeFragment
import com.redugsi.weatherapp.ui.main.MainFragment
import com.redugsi.weatherapp.ui.settings.SettingsFragment
import com.redugsi.weatherapp.ui.weather.WeatherFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule{
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeCameraFragment(): TakenPhotoPreviewFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeWeatherFragment(): WeatherFragment
}