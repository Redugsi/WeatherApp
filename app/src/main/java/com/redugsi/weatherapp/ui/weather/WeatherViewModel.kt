package com.redugsi.weatherapp.ui.weather

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.redugsi.weatherapp.api.WeatherApi
import com.redugsi.weatherapp.model.WeatherModel
import com.redugsi.weatherapp.model.WeatherResponse
import com.redugsi.weatherapp.util.StoreManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import kotlin.math.roundToInt

class WeatherViewModel @Inject constructor(var api: WeatherApi, var storeManager: StoreManager) : ViewModel() {

    var weatherLiveData: MutableLiveData<WeatherModel> = MutableLiveData()
    var onRequestFailed: MutableLiveData<Boolean> = MutableLiveData()

    init {
        getCurrentWeatherData()
    }

    private fun getCurrentWeatherData(){
        val weatherData = storeManager.getCurrentWeatherData()
        if (weatherData != null){
            weatherLiveData.value = weatherData
        }
    }

    fun getWeatherForecast() {
        api.getWeatherByCityName(storeManager.getChoosedCityName().trim().toLowerCase()).enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>?, t: Throwable?) {
                onRequestFailed.value = true
            }

            override fun onResponse(call: Call<WeatherResponse>?, response: Response<WeatherResponse>?) {
                if (response != null) {
                    val res = response!!.body()
                    if (res != null) {
                        val weatherData = convertResponseToWeatherModel(res)
                        weatherLiveData.value = weatherData
                        saveCurrentWeatherData(weatherData)
                        return
                    }
                }
                onRequestFailed.value = true
            }

        })
    }

    private fun convertResponseToWeatherModel(it: WeatherResponse): WeatherModel? {
        if (it?.main != null && it.name != null && it.main!!.temp != null && it.main!!.tempMin != null && it.main!!.tempMax != null && it.weather != null && it.weather!!.get(0) != null) {
            return WeatherModel(it.name!!, it.weather!!.get(0).id.toString(), it.main!!.temp!!.roundToInt().toString(),
                    it.main!!.tempMin!!.roundToInt().toString(), it.main!!.tempMax!!.roundToInt().toString())
        }
        return null
    }

    fun saveCurrentWeatherData(weatherData: WeatherModel?) {
        if (weatherData != null){
            storeManager.setCurrentWeatherData(weatherData)
        }
    }
}