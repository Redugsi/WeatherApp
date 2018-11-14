package com.redugsi.weatherapp.util

import android.content.Context
import android.location.Geocoder
import android.location.Location
import java.util.*

class Util{

    companion object {
        fun getCityName(context: Context, loc: Location):String?{
            val gcd = Geocoder(context, Locale.getDefault())
            val addresses = gcd.getFromLocation(loc.latitude, loc.longitude, 1)
            if (addresses.size > 0) {
                return addresses[0].locality
            } else {
                return null
            }
        }
    }
}