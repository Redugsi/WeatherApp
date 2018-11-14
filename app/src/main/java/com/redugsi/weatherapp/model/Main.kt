package com.redugsi.weatherapp.model

import com.google.gson.annotations.SerializedName

class Main(@SerializedName("temp")
               var temp: Double? = null,
               @SerializedName("pressure")
               var pressure: Double? = null,
               @SerializedName("humidity")
               var humidity: Double? = null,
               @SerializedName("temp_min")
               var tempMin: Double? = null,
               @SerializedName("temp_max")
               var tempMax: Double? = null)