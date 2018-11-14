package com.redugsi.weatherapp.model

import com.google.gson.annotations.SerializedName

data class Clouds(@SerializedName("all")
                var all: Double? = null)
