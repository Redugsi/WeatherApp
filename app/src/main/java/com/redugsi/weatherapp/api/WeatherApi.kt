package com.redugsi.weatherapp.api

import android.arch.lifecycle.LiveData
import com.redugsi.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeatherByCityName(@Query("cityName") cityName: String): LiveData<ApiResponse<WeatherResponse>>
}