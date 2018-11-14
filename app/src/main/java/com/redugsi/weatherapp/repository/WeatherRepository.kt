package com.redugsi.weatherapp.repository

import android.arch.lifecycle.LiveData
import com.redugsi.weatherapp.AppExecutors
import com.redugsi.weatherapp.api.ApiResponse
import com.redugsi.weatherapp.api.WeatherApi
import com.redugsi.weatherapp.db.WeatherDao
import com.redugsi.weatherapp.model.WeatherModel
import com.redugsi.weatherapp.model.WeatherResponse
import com.redugsi.weatherapp.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository  @Inject
internal constructor(
        private val appExecutors: AppExecutors,
        private val weatherDao: WeatherDao,
        private val api: WeatherApi) {

    fun createCity(cityName : String){
        appExecutors.diskIO().execute({ weatherDao.insert(WeatherModel(cityName))})
    }

//    fun getCity(cityName: String) : LiveData<Resource<WeatherModel>> {
//        return object : NetworkBoundResource<WeatherModel, WeatherResponse>(appExecutors){
//            override fun saveCallResult(item: WeatherResponse) {
//                if (item != null){
//                    weatherDao.uptadeWeather(item.cod!!.toString(),
//                            item.main!!.temp.toString(), item.main!!.tempMin.toString(),
//                            item.main!!.tempMax.toString())
//                }
//            }
//
//            override fun shouldFetch(data: WeatherModel?): Boolean {
//                return true
//            }
//
//            override fun loadFromDb(): LiveData<WeatherModel> {
//                return weatherDao.getCategories()
//            }
//
//            override fun createCall(): LiveData<ApiResponse<WeatherResponse>>? {
//                return null
//            }
//
//        }.asLiveData()

//    }

}