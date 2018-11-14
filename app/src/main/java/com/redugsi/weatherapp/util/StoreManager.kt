package com.redugsi.weatherapp.util

import android.content.Context
import com.orhanobut.hawk.Hawk
import com.redugsi.weatherapp.model.WeatherModel

class StoreManager constructor(context: Context) {

    init {
        Hawk.init(context).build()
    }

    fun isGpsSearchActive(): Boolean{
        val active = Hawk.get<Boolean>(Constants.STORE_IS_GPS_ACTIVE) ?: false
        return active
    }

    fun setGpsActive(isActive: Boolean){
        Hawk.put(Constants.STORE_IS_GPS_ACTIVE, isActive)
    }

    fun getChoosedCityName(): String{
        val name = Hawk.get<String>(Constants.STORE_CHOOSED_CITY_NAME) ?: "izmir"
        return name
    }

    fun setChoosedCityName(name: String){
        Hawk.put(Constants.STORE_CHOOSED_CITY_NAME, name)
    }

    fun setCurrentWeatherData(weatherData: WeatherModel){
        Hawk.put(Constants.STORE_LAST_WEATHER_DATA, weatherData)
    }

    fun getCurrentWeatherData(): WeatherModel?{
        val weatherData = Hawk.get<WeatherModel>(Constants.STORE_LAST_WEATHER_DATA)
        return weatherData
    }


}