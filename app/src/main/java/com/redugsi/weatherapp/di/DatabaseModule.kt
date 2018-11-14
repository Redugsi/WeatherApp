package com.redugsi.weatherapp.di

import android.app.Application
import android.arch.persistence.room.Room
import com.redugsi.weatherapp.db.WeatherDB
import com.redugsi.weatherapp.db.WeatherDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): WeatherDB {
        return Room.databaseBuilder(app, WeatherDB::class.java, "weather-db").build()
    }

    @Singleton
    @Provides
    fun provideWeatherDao(db: WeatherDB): WeatherDao {
        return db.weatherDao()
    }
}