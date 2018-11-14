package com.redugsi.weatherapp.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.redugsi.weatherapp.util.Constants

@Entity(tableName = Constants.TABLE_CITY)
data class WeatherModel(
        @PrimaryKey @ColumnInfo(name = Constants.ENTITY_NAME)var name: String = "",
        @ColumnInfo(name = Constants.ENTITY_CODE)var cod: String? = null,
        @ColumnInfo(name = Constants.ENTITY_TEMP) var temp: String? = null,
        @ColumnInfo(name = Constants.ENTITY_TEMP_MIN)var tempMin: String? = null,
        @ColumnInfo(name = Constants.ENTITY_TEMP_MAX)var tempMax: String? = null)