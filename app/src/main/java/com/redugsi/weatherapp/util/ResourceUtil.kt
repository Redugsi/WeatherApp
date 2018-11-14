package com.redugsi.weatherapp.util

import com.redugsi.weatherapp.R


class ResourceUtil {
    companion object {
        fun getResourceIdBuyWeatherConditionCode(code: String): Int{
           return when(code[0]){
                '2'-> R.drawable.thunderstorms
                '3'-> R.drawable.drizzle
                '5'-> R.drawable.slightdrizzle
                '6'-> R.drawable.snow
                '7'-> R.drawable.haze
                '8'-> R.drawable.sunny
                else-> R.drawable.sunny
            }
        }
    }
}