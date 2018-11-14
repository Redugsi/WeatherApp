package com.redugsi.weatherapp.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.redugsi.weatherapp.db.WeatherDao
import com.redugsi.weatherapp.model.WeatherModel
import com.redugsi.weatherapp.repository.CityRepository
import com.redugsi.weatherapp.util.Resource
import javax.inject.Inject

class MainViewModel @Inject constructor(repository: CityRepository): ViewModel(){

}