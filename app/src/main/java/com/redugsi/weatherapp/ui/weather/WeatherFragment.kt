package com.redugsi.weatherapp.ui.weather

import com.redugsi.weatherapp.R
import com.redugsi.weatherapp.databinding.FragmentWeatherBinding
import com.redugsi.weatherapp.ui.common.BaseInjectableFragment

class WeatherFragment: BaseInjectableFragment<WeatherViewModel, FragmentWeatherBinding>(){

    override fun getLayoutID(): Int {
        return R.layout.fragment_weather
    }

    override fun initViewModelClass(): Class<WeatherViewModel> {
        return WeatherViewModel::class.java
    }

}