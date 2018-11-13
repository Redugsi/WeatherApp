package com.redugsi.weatherapp.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.redugsi.weatherapp.di.scope.ViewModelKey
import com.redugsi.weatherapp.ui.common.WeatherAppViewModelFactory
import com.redugsi.weatherapp.ui.home.HomeViewModel
import com.redugsi.weatherapp.ui.main.MainViewModel
import com.redugsi.weatherapp.ui.settings.SettingsViewModel
import com.redugsi.weatherapp.ui.weather.WeatherViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(settingsViewModel: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WeatherViewModel::class)
    abstract fun bindWeatherViewModel(weatherViewModel: WeatherViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: WeatherAppViewModelFactory): ViewModelProvider.Factory
}