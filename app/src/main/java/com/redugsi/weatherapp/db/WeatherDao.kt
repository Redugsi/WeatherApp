package com.redugsi.weatherapp.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.redugsi.weatherapp.model.WeatherModel

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(weather: WeatherModel)

    @Query("SELECT * FROM city")
    fun getCategories(): LiveData<WeatherModel>

    @Query("UPDATE city SET code=:cod, temp_degree=:tempDegree, temp_min=:tempMin, temp_max=:tempMax")
    fun uptadeWeather(cod: String, tempDegree: String, tempMin: String, tempMax: String)
}