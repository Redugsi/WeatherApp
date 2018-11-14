package com.redugsi.weatherapp.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.redugsi.weatherapp.model.WeatherModel

@Database(entities = arrayOf(WeatherModel::class), version = 1, exportSchema = false)
abstract class WeatherDB : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}