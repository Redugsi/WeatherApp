package com.redugsi.weatherapp.api

import android.arch.lifecycle.LiveData
import com.redugsi.weatherapp.model.WeatherResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    fun getWeatherByCityName(@Query("q") cityName: String): Call<WeatherResponse>

    companion object {
        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    }
}